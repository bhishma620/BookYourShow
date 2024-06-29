package com.bhishma.bookyourshow.request.movie;

import com.bhishma.bookyourshow.validation.ValidDurationFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class MovieRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String cast;

    @NotNull
    private Date releaseDate;

    @ValidDurationFormat
    private String duration;

    @NotBlank
    private String language;

    @NotBlank
    private String type;

    @NotBlank
    private String country;
}

