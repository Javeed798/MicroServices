package com.microservice.hotel.service;

import com.microservice.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotelById(String hotelId);
}
