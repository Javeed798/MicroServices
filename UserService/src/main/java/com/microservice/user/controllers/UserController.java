package com.microservice.user.controllers;

import com.microservice.user.entities.User;
import com.microservice.user.exceptions.ResourceNotFoundException;
import com.microservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {

            User user1 = this.userService.createUser(user);
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        } catch (Exception e){
            throw new ResourceNotFoundException("User Cannot be created");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable  String userId) {
        User user = this.userService.getUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
}
