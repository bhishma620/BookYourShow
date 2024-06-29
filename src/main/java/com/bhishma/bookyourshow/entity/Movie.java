package com.bhishma.bookyourshow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


import java.util.Date;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String title;

    private String cast;

    private Date releaseDate;

    private String duration;

    private String language;

    private String type;

    private String country;

}
