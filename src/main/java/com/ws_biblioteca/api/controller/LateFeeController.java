package com.ws_biblioteca.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws_biblioteca.api.service.LateFeeService;

@RestController
@RequestMapping("/latefee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LateFeeController {

    @Autowired
    private LateFeeService lateFeeService;

    @GetMapping("/list")
    private ResponseEntity<Object> listLateFees() {
        try {
            return ResponseEntity.ok(lateFeeService.listLateFees());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/pay/{idMulta}")
    private ResponseEntity<Object> payLateFee(@PathVariable int idMulta) {
        try {
            return ResponseEntity.ok(lateFeeService.payLateFee(idMulta));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}