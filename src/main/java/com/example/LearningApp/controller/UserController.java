package com.example.LearningApp.controller;

import com.example.LearningApp.entity.User;
import com.example.LearningApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }


}
