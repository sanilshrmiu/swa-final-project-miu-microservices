package com.UserService.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.UserService.models.Roles;
import com.UserService.models.enums.ERoles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterObject {

    @NotBlank
    String username;
    
    @NotBlank
    String password;

    @NotBlank
    @Email
    String email;

    List<Roles> roles;
    
    public UserRegisterObject() {
        roles = new ArrayList<>();
    }

    public void addRoles(ERoles erole) {
        Roles role = new Roles();
        role.setRole(erole);
        roles.add(role);
    }

}
