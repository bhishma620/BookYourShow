package com.bhishma.bookyourshow.request.theater;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TheaterRequest {
    @NotNull
    private long cinemaHallId;
    @NotBlank
    private String name;
    @NotNull
    private int capacity;
}
