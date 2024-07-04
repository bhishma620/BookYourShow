package com.bhishma.bookyourshow.controller;

import com.bhishma.bookyourshow.request.theater.TheaterRequest;
import com.bhishma.bookyourshow.service.TheaterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("v1/theater")
@RestController
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping()
    ResponseEntity<String> add(@Valid @RequestBody TheaterRequest theater) {
        return theaterService.add(theater);
    }
}
