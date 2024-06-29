package com.bhishma.bookyourshow.controller;

import com.bhishma.bookyourshow.entity.Movie;
import com.bhishma.bookyourshow.request.movie.MovieRequest;
import com.bhishma.bookyourshow.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("v1/movies")
@RestController
@Validated
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping()
    public ResponseEntity<String> add(@Valid  @RequestBody MovieRequest movieRequest){

      return   movieService.save(movieRequest);
    }

    @GetMapping()
    public ResponseEntity<List<Movie>> getAll(){
        return movieService.getAll();
    }
    
}
