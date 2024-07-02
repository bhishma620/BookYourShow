package com.bhishma.bookyourshow.controller;

import com.bhishma.bookyourshow.request.cinemahall.CinemaHallRequest;
import com.bhishma.bookyourshow.service.CinemaHallService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/cinema-hall")
public class CinemaHallController {

    @Autowired
    CinemaHallService cinemaHallService;
    @PostMapping
    ResponseEntity<String> addCinemaHall(@Valid @RequestBody CinemaHallRequest cinemaHall){
        return cinemaHallService.addCinemaHall(cinemaHall);
    }
}
