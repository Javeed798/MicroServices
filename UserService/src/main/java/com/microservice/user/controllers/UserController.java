package com.microservice.user.controllers;

import com.microservice.user.entities.User;
import com.microservice.user.exceptions.ResourceNotFoundException;
import com.microservice.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
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
//    2 values are must for CircuitBreaker name and fallbackMethod
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable  String userId) {
        User user = this.userService.getUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

//    this is the fallback method which is created for the circuit breaker in the above method
    public ResponseEntity<User> ratingHotelFallback(String userId,Exception e) {
        log.info("Calling the fallback method because server is down" + e.getMessage());
        User dummyName = User.builder().name("dummy name")
                .email("dummy_mail@gmail.com")
                .about("Server is down soo dummy details are showing")
                .userId("54120451205412")
                .build();
        return new ResponseEntity<>(dummyName,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
}
