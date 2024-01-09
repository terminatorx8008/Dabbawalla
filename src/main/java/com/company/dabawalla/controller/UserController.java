package com.company.dabawalla.controller;

import com.company.dabawalla.dao.MenuRepo;
import com.company.dabawalla.dao.MessRepo;
import com.company.dabawalla.dao.UserRepo;
import com.company.dabawalla.entities.Customer;
import com.company.dabawalla.entities.Mess;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private MenuRepo menuRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MessRepo messRepo;
    private Customer user;

    @RequestMapping("/home")
    public String home(Model model, Principal principal, HttpSession session){
        session.setAttribute("user", userRepo.findByCustomerEmail(principal.getName()).get().getCustomerId());
        menuRepo.selectAllMenu();
        model.addAttribute("messes", messRepo.findAll() );
        return "Normal/home";
    }
    @RequestMapping("/profile")
    public String profile(Model model, Principal principal){
        Optional<Customer> optionalUser =userRepo.findByCustomerEmail(principal.getName());
        user= optionalUser.get();
        model.addAttribute("user", optionalUser.get());
        String imagePath = "classpath:static/IMG/Customer/" + user.getCustomerId() + ".jpg";
        Resource resource = resourceLoader.getResource(imagePath);
        boolean imageExists = resource.exists();
        // Set the image URL accordingly
        if (imageExists) {
            model.addAttribute("profileImage", "/img/Customer/" + user.getCustomerId() + ".jpg");
        } else {
            model.addAttribute("profileImage", "https://fakeimg.pl/600x400?text=No+Image");
        }
        return "Normal/profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(Customer customer, @RequestParam("Image") MultipartFile file , Principal principal){
        try{
            user.setCustomerName(customer.getCustomerName());
            user.setCustomerAddress(customer.getCustomerAddress());
            user.setCustomerContact(customer.getCustomerContact());
            user.setCustomerEmail(customer.getCustomerEmail());
            userRepo.save(user);
            if (!file.isEmpty()) {
                File saveFile = new ClassPathResource("static/IMG/Customer").getFile();
                Path path = Path.of(saveFile.getAbsolutePath() + File.separator + user.getCustomerId() + ".jpg");
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/user/profile";
    }

    @RequestMapping("/fav")
    public String fav(){
        return "Normal/fav";
    }
    @RequestMapping("/settings")
    public String settings(){
        return "Normal/settings";
    }
    @RequestMapping("/mess-details/{messId}")
    public String messDetails(@PathVariable("messId") int messId, Model model) {
        Mess mess = messRepo.findById(messId).get();
        model.addAttribute("mess", mess);
        return "Normal/mess-details";
    }
}
