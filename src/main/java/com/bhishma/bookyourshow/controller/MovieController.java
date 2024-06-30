package com.bhishma.bookyourshow.controller;

import com.bhishma.bookyourshow.entity.Movie;
import com.bhishma.bookyourshow.request.movie.MovieRequest;
import com.bhishma.bookyourshow.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<List<Movie>>getAll(@RequestParam(defaultValue="0") int pageNo,
                                             @RequestParam(defaultValue="10") int pageSize){
        return movieService.getAll(pageNo,pageSize);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>>searchByTitle(@RequestParam("title") String title,
                                             @RequestParam(defaultValue="0") int pageNo,
                                             @RequestParam(defaultValue="10") int pageSize){

        return movieService.searchByTitle(title,pageNo,pageSize);
    }

    @GetMapping("/search/byCategory")
    public ResponseEntity<List<Movie>>searchByCategory(@RequestParam("category") String category,
                                                    @RequestParam(defaultValue="0") int pageNo,
                                                    @RequestParam(defaultValue="10") int pageSize){

        return movieService.searchByCategory(category,pageNo,pageSize);
    }

}
