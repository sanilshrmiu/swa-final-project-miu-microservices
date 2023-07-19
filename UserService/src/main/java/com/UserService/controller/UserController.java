// package com.UserService.controller;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.dao.DataIntegrityViolationException;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.servlet.view.RedirectView;

// import com.UserService.dto.ErrorResponse;
// import com.UserService.exceptions.UserCreationException;
// import com.UserService.exceptions.UserNotFoundException;
// import com.UserService.models.User;
// import com.UserService.service.UserService;

// import jakarta.validation.Valid;


// @RestController
// // @RequestMapping(value = "/api/users")
// public class UserController {

//     @Autowired
//     UserService userServ;

//     @GetMapping("")
//     RedirectView redirecting() {
//         return new RedirectView("/api/user");
//     }

//     @GetMapping("/")
//     List < User > getAllUsers() {
//         return userServ.getAllUsers();
//     }

//     @GetMapping("/{id}")
//     User getUserById(@PathVariable Long id) {
//         try {
//             Optional < User > user = userServ.getUserById(id);
//             return user.get();

//         } catch (Exception e) {
//             // TODO: handle exception
//             throw new UserNotFoundException("User not found");
//         }
//     }

//     @PostMapping("")
//     ResponseEntity < ? > registerUser(@Valid @RequestBody User newUser) {
//             Optional < User > newUserAdded = userServ.saveUser(newUser);
//             if (!newUserAdded.isPresent()) {
//                 throw new UserCreationException("Error creating User");
//             }
//             return ResponseEntity.ok(newUserAdded.get());


//         }



//         // helperFunctions.attachJWT(newUserAdded.get());


    



// }