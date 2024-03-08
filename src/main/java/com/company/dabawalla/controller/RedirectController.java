package com.company.dabawalla.controller;

import com.company.dabawalla.dao.MenuRepo;
import com.company.dabawalla.dao.MessRepo;
import com.company.dabawalla.dao.UserRepo;
import com.company.dabawalla.entities.Menu;
import com.company.dabawalla.entities.Mess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class RedirectController {
    @GetMapping("/redirectBasedOnRole")
    public String redirectBasedOnRole(Authentication authentication) {
        if (authentication != null) {
            if (authentication.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_MESS"))) {
                return "redirect:/mess/home";
            } else if (authentication.getAuthorities().stream().anyMatch(grantedAuthority ->
                    grantedAuthority.getAuthority().equals("ROLE_USER"))) {
                return "redirect:/user/index";
            }
        }
        return "redirect:/default-home";
    }
}

