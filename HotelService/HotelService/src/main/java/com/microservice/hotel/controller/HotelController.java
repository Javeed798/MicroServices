package com.microservice.hotel.controller;

import com.microservice.hotel.entity.Hotel;
import com.microservice.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel hotel1 = hotelService.createHotel(hotel);
        return new ResponseEntity<>(hotel1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> allHotels = this.hotelService.getAllHotels();
        return new ResponseEntity<>(allHotels,HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable  String hotelId) {
        Hotel hotelById = this.hotelService.getHotelById(hotelId);
        return new ResponseEntity<>(hotelById,HttpStatus.OK);
    }



}
