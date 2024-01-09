package com.company.dabawalla.controller;

import com.company.dabawalla.dao.MessRepo;
import com.company.dabawalla.entities.Mess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private MessRepo messRepo;
    @GetMapping("/search/{query}")
    public ResponseEntity<?> search(@PathVariable("query") String query, Model model) {
        List<Mess> results = messRepo.findByMessNameContainingIgnoreCase(query);
        return ResponseEntity.ok(results);
    }
}
