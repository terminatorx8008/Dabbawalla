package com.company.dabawalla.controller;

import com.company.dabawalla.dao.*;
import com.company.dabawalla.entities.*;
import com.company.dabawalla.helper.Message;
import com.company.dabawalla.service.JavaMailServiceImpl;
import jakarta.mail.Session;
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
import java.util.ArrayList;
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
    private MessImagesRepo messImagesRepo;
    @Autowired
    private MessReviewRepo messReviewRepo;
    @Autowired
    private SubsRepo subsRepo;

    @RequestMapping("/home")
    public String adminHome(Model model, Principal principal) {
        this.mess = messRepo.findByMessEmail(principal.getName());
//        get subscribed customers
        List<Subscribtion> subscribtions = this.mess.getSubscribtions();
        List<Customer> customers = new ArrayList<>();
        subscribtions.forEach(subscribtion -> customers.add(subscribtion.getCustomer()));
        model.addAttribute("customers", customers);
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


    @RequestMapping("/reviews")
    public String adminReviews(Model model) {
        List<MessReview> messReview = messReviewRepo.findMessReviewByMess(mess);
        model.addAttribute("messReviews",messReview);
        return "Admin/review";
    }

    @RequestMapping("/profile")
    public String adminProfile(Model model, Principal principal) {
        Mess mess = messRepo.findByMessEmail(principal.getName());
        System.out.println(mess);
        model.addAttribute("mess", mess);
        return "Admin/profile";
    }

    @RequestMapping("/order-details")
    public String adminOrder(Model model) {
        List<Subscribtion> subscribtions = subsRepo.findByMessWhereStatusISFalse(mess);
        if(subscribtions.size() == 0){
            model.addAttribute("message", new Message("No new order", "danger"));
            return "Admin/order";
        }
        model.addAttribute("subscribtions",subscribtions);
        return "Admin/order";
    }
    @RequestMapping("/subscription-details")
    public String adminSubscription(Model model) {
        List<Subscribtion> subscribtions = subsRepo.findByMessWhereStatusISTrue(mess);
        if(subscribtions.size() == 0){
            model.addAttribute("message", new Message("No new order", "danger"));
            return "Admin/subscription";
        }
        model.addAttribute("subscribtions",subscribtions);
        return "Admin/subscription";
    }

    @RequestMapping("/settings")
    public String adminSettings() {
        return "Admin/settings";
    }

    @PostMapping("/update-profile")
    public String updateProfile(Mess mess, @RequestParam("files") List<MultipartFile> files, HttpSession session){
        try {
            Mess oldMess = this.mess;
            oldMess.setMessName(mess.getMessName());
            oldMess.setMessAddress(mess.getMessAddress());
            oldMess.setMessContact(mess.getMessContact());
            oldMess.setMessEmail(mess.getMessEmail());
            messRepo.save(oldMess);

            // Assuming you want to delete all previous images and add new ones
            // Step 1: Delete previous images from storage

            // Note: Implement deleteImagesFromStorage method to delete images from the file system

            // Step 2: Delete previous images from the database
            List<MessImages> existingImages = oldMess.getMessImage();
            if(existingImages != null){
                for(MessImages img : existingImages){
                    messImagesRepo.delete(img);
                }
            }

            // Step 3: Save new images
            for (MultipartFile file : files) {
                try {
                    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                    File saveFile = new ClassPathResource("static/IMG/Mess").getFile();
                    Path path = Path.of(saveFile.getAbsolutePath() + File.separator + fileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                    MessImages messImages = new MessImages();
                    messImages.setMess(oldMess);
                    messImages.setMessImage(fileName);
                    messImagesRepo.save(messImages);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            session.setAttribute("Profilemessage", new Message("Profile updated successfully", "success"));
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("Profilemessage", new Message("Something went wrong", "danger"));
        }
        return "redirect:/mess/profile";
    }

    @RequestMapping("/accept-order/{subscribtionId}")
    public String acceptOrder(@PathVariable("subscribtionId") int subscribtionId, HttpSession session ){
        Subscribtion subscribtion = mess.getSubscribtions().stream().filter(subscribtion1 -> subscribtion1.getSubscribtionId() == subscribtionId).findFirst().get();
        subscribtion.setSubscribtionStatus(true);
        mess.getSubscribtions().add(subscribtion);
        JavaMailServiceImpl mail = new JavaMailServiceImpl();
        mail.send(subscribtion.getCustomer().getCustomerEmail(), "Your Order is being accepted", "Your order is being accepted by the mess");
        messRepo.save(mess);
        session.setAttribute("message", new Message("Order accepted successfully", "success"));
        return "redirect:/mess/order-details";
    }
    @RequestMapping("/reject-order/{subscribtionId}")
    public String rejectOrder(@PathVariable("subscribtionId") int subscribtionId, HttpSession session ){
        Subscribtion subscribtion = subsRepo.findById(subscribtionId).get();
        if (subscribtion.getCustomer() != null) {
            subscribtion.getCustomer().removeSubscribtion(subscribtion);
        }
        if (subscribtion.getMess() != null) {
            subscribtion.getMess().removeSubscribtion(subscribtion);
        }
        JavaMailServiceImpl mail = new JavaMailServiceImpl();
        mail.send(subscribtion.getCustomer().getCustomerEmail(), "Your Order is being rejected", "Your order is being rejected by the mess");
        subsRepo.delete(subscribtion);
        session.setAttribute("message", new Message("Order rejected successfully", "success"));
        return "redirect:/mess/order-details";
    }
}
