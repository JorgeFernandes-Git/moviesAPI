package com.jorgefernandes.movies.controllers;

import com.jorgefernandes.movies.domain.user.User;
import com.jorgefernandes.movies.dtos.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jorgefernandes.movies.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private UserService userService;

//    @PostMapping
//    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
//        // Implement the logic to authenticate the user based on the provided login data
//        User authenticatedUser = userService.authenticateUser(loginDTO);
//
//        if (authenticatedUser != null) {
//            // Return the authenticated user's data or a token for authentication
//            return new ResponseEntity<>("Login successful", HttpStatus.OK);
//        } else {
//            // Handle authentication failure
//            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
//        }
//    }

    @PostMapping
    public ResponseEntity<User> loginUser(@RequestBody LoginDTO loginDTO) {
        User authenticatedUser = userService.authenticateUser(loginDTO);

        if (authenticatedUser != null) {
            return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Or HttpStatus.NOT_FOUND, etc., to indicate authentication failure
        }
    }
}
