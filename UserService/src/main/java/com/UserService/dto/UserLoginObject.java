package com.UserService.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class UserLoginObject implements Serializable {

    @NotBlank
    String username;
    @NotBlank
    String password;
    
     public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
