package com.ws_biblioteca.api.model;
import lombok.Data;

@Data
public class User {
    private String user;
    private String password;
    
    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }
}
