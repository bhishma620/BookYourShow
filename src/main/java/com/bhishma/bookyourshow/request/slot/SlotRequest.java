package com.bhishma.bookyourshow.request.slot;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;


@Data
public class SlotRequest {

    @NotNull
    private long cinemaHallId;

    @NotNull
    private long movieId;

    @NotNull
    private long theaterId;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalDate date;
}
