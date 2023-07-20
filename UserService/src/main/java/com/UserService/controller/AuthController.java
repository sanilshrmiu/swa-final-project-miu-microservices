package com.UserService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserService.models.User;
import com.UserService.service.AuthService;
import com.UserService.service.UserService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "users")
public class AuthController {

    @Autowired
    UserService userServ;
    @Autowired
    AuthService authServ;

    @PostMapping(value="login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User userObjToBeAuthenticated) {
        String jwtToken = authServ.authenticateUser(userObjToBeAuthenticated);
        
        Map<String, Object> response = new HashMap<>();
        response.put("jwtToken", jwtToken);
        return ResponseEntity.ok(response);
    }
    
    
}
