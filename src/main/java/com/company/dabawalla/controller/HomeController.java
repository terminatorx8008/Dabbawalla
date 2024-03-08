package com.company.dabawalla.controller;

import com.company.dabawalla.dao.MessImagesRepo;
import com.company.dabawalla.dao.MessRepo;
import com.company.dabawalla.dao.UserRepo;
import com.company.dabawalla.entities.Customer;
import com.company.dabawalla.entities.Mess;
import com.company.dabawalla.entities.MessImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MessRepo messRepo;
    @Autowired
    private MessImagesRepo messImagesRepo;
    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title", "Login - Dabawalla");
        model.addAttribute("user", new Customer());
        return "login";
    }
    @PostMapping("/do_register")
    public String registerUser(@ModelAttribute("user") Customer user , BindingResult result){
        try{
            if(result.hasErrors()){
                System.out.println("ERROR "+result.toString());
                return "login";
            }
            user.setCustomerRole("ROLE_USER");
            user.setCustomerPassword(bCryptPasswordEncoder.encode(user.getCustomerPassword()));
            this.userRepo.save(user);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return "login";

    }
    @GetMapping("/register-mess")
    public String registerMess(@ModelAttribute("user") Customer user , BindingResult result){
        return "nregisterMess";

    }
    @PostMapping("/do_register-mess")
    public String doRegisterMess(@ModelAttribute("user") Mess mess , @RequestParam("photos") List<MultipartFile> files , BindingResult result){
        try{
            if(result.hasErrors()){
                System.out.println("ERROR "+result.toString());
                return "login";
            }
            if (mess.getMessImage() == null) {
                mess.setMessImage(new ArrayList<>());
            }
            mess.setMessRole("ROLE_MESS");
            for (MultipartFile file : files) {
                try {
                    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

                    File saveFile = new ClassPathResource("static/IMG/Mess").getFile();
                    Path path = Path.of(saveFile.getAbsolutePath() + File.separator + fileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                    MessImages messimage = new MessImages();
                    messimage.setMessImage(fileName);
                    messimage.setMess(mess);  // Set the Mess entity in the MessImage
                    mess.getMessImage().add(messimage);
                    mess.setMessPassword(bCryptPasswordEncoder.encode(mess.getMessPassword()));
                    this.messRepo.save(mess);
                    messImagesRepo.save(messimage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
        return "redirect:/";
    }

    @RequestMapping("/error-login")
    public String loginError(Model model){
        model.addAttribute("title", "Login - Dabawalla");
        model.addAttribute("error", "Invalid Credentials");
        return "errorPage";
    }


}
