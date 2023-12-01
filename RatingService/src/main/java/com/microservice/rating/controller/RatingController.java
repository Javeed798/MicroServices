package com.microservice.rating.controller;

import com.microservice.rating.entities.Hotel;
import com.microservice.rating.entities.Rating;
import com.microservice.rating.service.RatingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
@Slf4j
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
    @CircuitBreaker(name = "ratingHotelRatingBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
        List<Rating> allRatings = this.ratingService.getRatingByHotelId(hotelId);
        return new ResponseEntity<>(allRatings,HttpStatus.OK);
    }

    public ResponseEntity<List<Rating>> ratingHotelFallback(String hotelId, Exception e) {
       log.info("Rating server is Down soo unable to get ratings"+e.getMessage());
        List list = new ArrayList<>();
        Rating build = Rating.builder().rating(3).hotelId("65230").ratingId("some random").feedback("Sorry server is down this is dummmy").build();
        list.add(build);
        list.add(build);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
