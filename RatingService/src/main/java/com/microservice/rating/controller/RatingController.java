package com.microservice.rating.controller;

import com.microservice.rating.entities.Rating;
import com.microservice.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating rating1 = this.ratingService.createRating(rating);
        return new ResponseEntity<>(rating1, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Rating>> getAlLRatings() {
        List<Rating> allRatings = this.ratingService.getAllRatings();
        return new ResponseEntity<>(allRatings,HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
        List<Rating> allRatings = this.ratingService.getRatingByUserId(userId);
        return new ResponseEntity<>(allRatings,HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
        List<Rating> allRatings = this.ratingService.getRatingByHotelId(hotelId);
        return new ResponseEntity<>(allRatings,HttpStatus.OK);
    }
}
