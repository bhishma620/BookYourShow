package com.bhishma.bookyourshow.controller;

import com.bhishma.bookyourshow.entity.Theater;
import com.bhishma.bookyourshow.request.theater.TheaterRequest;
import com.bhishma.bookyourshow.response.theater.TheaterResponse;
import com.bhishma.bookyourshow.service.TheaterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v1/theater")
@RestController
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping()
    ResponseEntity<String> add(@Valid @RequestBody TheaterRequest theater) {
        return theaterService.add(theater);
    }

    @GetMapping()
    ResponseEntity<TheaterResponse> getTheaterDetailsByCinemaHallIdAndTheaterId
            (@RequestParam("cinema-hallId") long cinemaHallId,
             @RequestParam("theaterId") long theaterId) {
        return theaterService.getTheaterDetailsByCinemaHallIdAndTheaterId(cinemaHallId,theaterId);
    }
}
