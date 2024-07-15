package com.bhishma.bookyourshow.response.slot;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SlotResponse {

    private long cinemaHallId;

    private long movieId;

    private long theaterId;

    private LocalTime startTime;

    private LocalDate date;
}
