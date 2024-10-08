package com.bhishma.bookyourshow.controller;

import com.bhishma.bookyourshow.entity.CinemaHall;
import com.bhishma.bookyourshow.request.cinemahall.CinemaHallRequest;
import com.bhishma.bookyourshow.service.CinemaHallService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/cinema-hall")
public class CinemaHallController {

    @Autowired
    CinemaHallService cinemaHallService;
    @PostMapping()
    ResponseEntity<String> addCinemaHall(@Valid @RequestBody CinemaHallRequest cinemaHall){
        return cinemaHallService.addCinemaHall(cinemaHall);
    }

    //get By City name
    @GetMapping("/search/byCity")
    ResponseEntity<List<CinemaHall>> getCinemaHall(@RequestParam ("city") String city,
                                                   @RequestParam (defaultValue = "0") int pageNo,
                                                   @RequestParam (defaultValue = "10") int pageSize){
        return cinemaHallService.getCinemaHallByCity(city,pageNo,pageSize);
    }

    // get By CinemaHall name
    @GetMapping("/search/byName")
    ResponseEntity<List<CinemaHall>> getCinemaHallByName(@RequestParam ("name") String name,
                                                   @RequestParam (defaultValue = "0") int pageNo,
                                                   @RequestParam (defaultValue = "10") int pageSize){
        return cinemaHallService.getCinemaHallByName(name,pageNo,pageSize);
    }

    //update details

    @PutMapping("/{id}")
    ResponseEntity<String> updateCinemaHall(@PathVariable("id") long id,
                                            @Valid @RequestBody CinemaHallRequest cinemaHall){
        return cinemaHallService.updateCinemaHall(id,cinemaHall);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable("id") long id){
        return cinemaHallService.delete(id);
    }





}
