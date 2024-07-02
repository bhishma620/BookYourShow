package com.bhishma.bookyourshow.request.cinemahall;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Valid
public class CinemaHallRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @NotBlank
    private String location;
}
