package com.company.dabawalla.controller;

import com.company.dabawalla.dao.MenuRepo;
import com.company.dabawalla.dao.MessImagesRepo;
import com.company.dabawalla.dao.MessRepo;
import com.company.dabawalla.dao.OrderRepo;
import com.company.dabawalla.entities.Menu;
import com.company.dabawalla.entities.Mess;
import com.company.dabawalla.entities.MessImages;
import com.company.dabawalla.entities.Orders;
import com.company.dabawalla.helper.Message;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/mess")
public class MessController {
    private Mess mess;
    @Autowired
    private MessRepo messRepo;
    @Autowired
    private MenuRepo menuRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private MessImagesRepo messImagesRepo;

    @RequestMapping("/home")
    public String adminHome(Model model, Principal principal) {
        this.mess = messRepo.findByMessEmail(principal.getName());
        return "Admin/home";
    }

    @RequestMapping("/menu")
    public String adminMenu(Model model) {
        model.addAttribute("menu", new Menu());
        List<Menu> menu = menuRepo.findByMessId(mess.getMessId());
        model.addAttribute("menuList", menu);
        return "Admin/menu";
    }

    @PostMapping("/add-menu")
    public String addMenu(@ModelAttribute("menu") Menu menu, @RequestParam("Image") MultipartFile file, HttpSession session, Model model) {
        try {
            System.out.println(menu);
            mess.getMenuItems().add(menu);
            menu.setMess(mess);
            System.out.println(mess);
            this.messRepo.save(mess);

            if (!file.isEmpty()) {
                File saveFile = new ClassPathResource("static/IMG/Menu").getFile();
                Path path = Path.of(saveFile.getAbsolutePath() + File.separator + menu.getMenuId() + ".jpg");
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            model.addAttribute("menu", new Menu());
            session.setAttribute("message", new Message("Menu added successfully", "success"));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("menu", new Menu());
            session.setAttribute("message", new Message("Something went wrong", "danger"));
        }
        return "redirect:/mess/menu";
    }

    @PostMapping("/update-menu")
    public String updateMenu(@ModelAttribute("menu") Menu menu, @RequestParam("Image") MultipartFile file, HttpSession session, Model model) {
        try {
            Menu oldMenu = menuRepo.findByMenuId(menu.getMenuId());
            oldMenu.setMenuName(menu.getMenuName());
            oldMenu.setMenuDescription(menu.getMenuDescription());
            oldMenu.setMenuPrice(menu.getMenuPrice());
            oldMenu.setMenuDay(menu.getMenuDay());
            oldMenu.setMenuTiming(menu.getMenuTiming());
            menuRepo.save(oldMenu);
            if (!file.isEmpty()) {
                File saveFile = new ClassPathResource("static/IMG/Menu").getFile();
                Path path = Path.of(saveFile.getAbsolutePath() + File.separator + menu.getMenuId() + ".jpg");
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            model.addAttribute("menu", new Menu());
            session.setAttribute("message", new Message("Menu updated successfully", "success"));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("menu", new Menu());
            session.setAttribute("message", new Message("Something went wrong", "danger"));
        }
        return "redirect:/mess/menu";
    }

    @RequestMapping("/delete-menu/{menuId}")
    public String deleteMenu(@PathVariable("menuId") int menuId, Model model, HttpSession session) {
        try {
            Menu menu = menuRepo.findByMenuId(menuId);
            Mess mess = menu.getMess();
            mess.getMenuItems().remove(menu);
            this.messRepo.save(mess);
            this.menuRepo.delete(menu);
//            delete image from static folder
            File saveFile = new ClassPathResource("static/IMG/Menu").getFile();
            Path path = Path.of(saveFile.getAbsolutePath() + File.separator + menuId + ".jpg");
            Files.deleteIfExists(path);

            model.addAttribute("menu", new Menu());
            session.setAttribute("message", new Message("Menu deleted successfully", "success"));
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", new Message("Something went wrong", "danger"));
        }
        return "redirect:/mess/menu";
    }

    @RequestMapping("/order-details")
    public String adminDelivery(Model model) {
//        findByOrderStatus and order.messId = mess.messId
        System.out.println(mess.getMessId());
        List<Orders> orders = orderRepo.findByOrderStatusAndMessId("pending", mess.getMessId());
        model.addAttribute("orders", orders);
        return "Admin/order";
    }

    @RequestMapping("/reviews")
    public String adminReviews() {
        return "Admin/review";
    }

    @RequestMapping("/profile")
    public String adminProfile(Model model, Principal principal) {
        Mess mess = messRepo.findByMessEmail(principal.getName());
        System.out.println(mess);
        model.addAttribute("mess", mess);
        return "Admin/profile";
    }

    @PostMapping("/upload-images")
    public String adminUploadImages(@RequestParam("files") List<MultipartFile> files) {
        System.out.println("adding files");
        for (MultipartFile file : files) {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());

                File saveFile = new ClassPathResource("static/IMG/Mess").getFile();
                Path path = Path.of(saveFile.getAbsolutePath() + File.separator + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                MessImages messimage = new MessImages();
                messimage.setMessImage(fileName);
                messimage.setMess(this.mess);  // Set the Mess entity in the MessImage
                this.mess.getMessImage().add(messimage);  // Add the MessImage in the Mess entity
                this.messRepo.save(this.mess);
                this.messImagesRepo.save(messimage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/mess/profile";
    }


    @RequestMapping("/subscription-details")
    public String adminSubscription() {
        return "Admin/subscription";
    }

    @RequestMapping("/settings")
    public String adminSettings() {
        return "Admin/settings";
    }

    @PostMapping("/update-profile")
    public String updateProfile(Mess mess, Principal principal) {
        try {
            Mess oldMess = messRepo.findByMessEmail(principal.getName());
            oldMess.setMessName(mess.getMessName());
            oldMess.setMessAddress(mess.getMessAddress());
            oldMess.setMessContact(mess.getMessContact());
            oldMess.setMessEmail(mess.getMessEmail());
            messRepo.save(oldMess);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/mess/profile";
    }


}
