package com.microservice.rating.service;

import com.microservice.rating.entities.Rating;

import java.util.List;

public interface RatingService {

//    create
    Rating createRating(Rating rating);

//    get all ratings
    List<Rating> getAllRatings();

//    get all by userId
    List<Rating> getRatingByUserId(String userId);

//    get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
