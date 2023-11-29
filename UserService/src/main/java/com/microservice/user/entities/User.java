package com.microservice.user.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String userId;

    private String name;

    private String email;

    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
