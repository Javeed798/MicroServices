package com.microservice.hotel.service.impl;

import com.microservice.hotel.entity.Hotel;
import com.microservice.hotel.repositories.HotelRepository;
import com.microservice.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    public HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String string = UUID.randomUUID().toString();
        hotel.setHotelId(string);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow();
        return hotel;
    }
}
