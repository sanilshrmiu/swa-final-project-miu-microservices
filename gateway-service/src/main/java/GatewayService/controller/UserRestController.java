package GatewayService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import GatewayService.dto.UserLoginObject;
import GatewayService.dto.UserRegisterObject;
import GatewayService.model.User;
import GatewayService.service.IUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserRestController {

    private final IUserService userService;

    public UserRestController(@Autowired IUserService userService) {

        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(produces = "application/json")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping(value = "login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody UserLoginObject user) {
         Map<String, Object> response = new HashMap<>();
         String token = userService.login(user);
         response.put("jwtToken", token);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody UserRegisterObject user) {
         User createdUser = userService.register(user);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);

    }

}
