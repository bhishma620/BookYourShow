package com.bhishma.bookyourshow.service;

import com.bhishma.bookyourshow.entity.Movie;
import com.bhishma.bookyourshow.request.movie.MovieRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {

    ResponseEntity<String> save(MovieRequest movie);


    ResponseEntity<List<Movie>> getAll(int pageNo,int offset);
}
