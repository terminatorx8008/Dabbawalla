package com.company.dabawalla.controller;

import com.company.dabawalla.dao.MenuRepo;
import com.company.dabawalla.dao.MessRepo;
import com.company.dabawalla.entities.Menu;
import com.company.dabawalla.entities.Mess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestAPIs {
    @Autowired
    private MessRepo messRepo;
    @Autowired
    private MenuRepo menuRepo;
    @GetMapping("/search/{query}")
    public ResponseEntity<?> search(@PathVariable("query") String query, Model model) {
        List<Mess> results = messRepo.findByMessNameContainingIgnoreCase(query);
        return ResponseEntity.ok(results);
    }
}
