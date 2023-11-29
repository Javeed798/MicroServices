package com.microservice.hotel.repositories;

import com.microservice.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
}
