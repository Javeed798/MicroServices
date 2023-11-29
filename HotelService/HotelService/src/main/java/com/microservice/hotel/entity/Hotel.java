package com.microservice.hotel.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {

    @Id
    private String hotelId;

    private String name;

    private String location;

    private String about;
}
