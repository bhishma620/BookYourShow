package com.bhishma.bookyourshow.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long bookingId;

    private long cinemaHallId;

    private long theaterId;

    private long slotId;

    private long ticketId;

    private long userId;

    private int status;

    private String time;

}
