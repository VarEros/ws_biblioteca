package com.ws_biblioteca.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ws_biblioteca.api.model.User;
import com.ws_biblioteca.api.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public ResponseEntity<Object> login(@RequestParam String user, @RequestParam String password) {
        try {
            User userObj = new User(user, password);
            boolean userLogged = userService.login(userObj);
            return ResponseEntity.ok(userLogged);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
