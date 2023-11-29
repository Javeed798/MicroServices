package com.microservice.user.service.impl;

import com.microservice.user.entities.Hotel;
import com.microservice.user.entities.Rating;
import com.microservice.user.entities.User;
import com.microservice.user.exceptions.ResourceNotFoundException;
import com.microservice.user.repositories.UserRepository;
import com.microservice.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User createUser(User user) {
        String randomUID = UUID.randomUUID().toString();
        user.setUserId(randomUID);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find user with given userId" + userId));
//        here for now we are getting the ratings as null soo now we need to fetch this as well
//        soo we use RatingService Api here for fetching the userService along with ratings
//        http://localhost:8083/ratings/users/{userId};
//        soo we need one client here soo we will be using RestTemplate.
//        java.lang.ClassCastException: class java.util.LinkedHashMap cannot be cast to class com.microservice.user.entities.Rating
//          (java.util.LinkedHashMap is in module java.base of loader 'bootstrap'; com.microservice.user.entities.Rating is in unnamed module of loader 'app')
    // => so we cannot use ArrayList directly return the Rating[] and then convert that into the ArrayList or List ur wish
//        List<Rating> ratings = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserId(), ArrayList.class);


        /*
                => Soo when we removed our localhost and portNumber and we started using NAMES soo in order to get this work
                    we need to define an annotation known as LoadBalanced in RestTemplate Config class. -> goto MyConfig
         */
        Rating[] rating1 = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

        List<Rating> ratings = Arrays.stream(rating1).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            ResponseEntity<Hotel> entity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = entity.getBody();
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        log.info("{}",ratings);
        user.setRatings(ratingList);
        return user;
    }
}
