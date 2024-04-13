package com.ws_biblioteca.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws_biblioteca.api.model.User;
import com.ws_biblioteca.api.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(User user) {
        String result = userRepository.iniciarSesion(user);
        if(result.equals("CREDENCIALES CORRECTAS")) {
            return true;
        } else {
            return false;
        }
    }
}
