package com.bhishma.bookyourshow.controller;

import com.bhishma.bookyourshow.entity.Theater;
import com.bhishma.bookyourshow.request.theater.TheaterRequest;
import com.bhishma.bookyourshow.response.theater.TheaterResponse;
import com.bhishma.bookyourshow.service.TheaterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("v1/theater")
@RestController
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping()
    ResponseEntity<String> add(@Valid @RequestBody TheaterRequest theater) {
        return theaterService.add(theater);
    }

    @GetMapping("/search/byTheater")
    ResponseEntity<TheaterResponse> getTheaterDetailsByCinemaHallIdAndTheaterId
            (@RequestParam("cinema-hallId") long cinemaHallId,
             @RequestParam("theaterId") long theaterId) {
        return theaterService.getTheaterDetailsByCinemaHallIdAndTheaterId(cinemaHallId, theaterId);
    }

    @GetMapping("/search/byCinemaHall")
    ResponseEntity<List<TheaterResponse>> getAllByCinemaHallId(@RequestParam("cinema-hallId") long cinemaHallId,
                                                               @RequestParam(defaultValue = "0") int pageNo,
                                                               @RequestParam(defaultValue = "10") int pageSize) {

        return theaterService.getAllByCinemaHallId(cinemaHallId,pageNo,pageSize);

    }

    @PutMapping("{id}")
    ResponseEntity<String>update(@PathVariable("id") long id,
                                 @Valid @RequestBody TheaterRequest theaterRequest){
        return theaterService.update(id,theaterRequest);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String>delete(@PathVariable("id") long id){
        return theaterService.delete(id);
    }

}
