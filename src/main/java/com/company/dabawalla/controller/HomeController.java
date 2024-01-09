package com.company.dabawalla.controller;

import com.company.dabawalla.dao.MessRepo;
import com.company.dabawalla.dao.UserRepo;
import com.company.dabawalla.entities.Customer;
import com.company.dabawalla.entities.Mess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;

@Controller
public class HomeController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MessRepo messRepo;
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
        return "registerMess";

    }
    @PostMapping("/do_register-mess")
    public String doRegisterMess(@ModelAttribute("user") Mess mess , BindingResult result){
        try{
            if(result.hasErrors()){
                System.out.println("ERROR "+result.toString());
                return "login";
            }
            mess.setMessRole("ROLE_MESS");
            System.out.println(mess.getMessPassword());
            mess.setMessPassword(bCryptPasswordEncoder.encode(mess.getMessPassword()));
            this.messRepo.save(mess);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return "redirect:/";

    }
}
