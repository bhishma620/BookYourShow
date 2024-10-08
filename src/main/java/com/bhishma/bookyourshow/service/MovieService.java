package com.bhishma.bookyourshow.service;

import com.bhishma.bookyourshow.entity.Movie;
import com.bhishma.bookyourshow.request.movie.MovieRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {

    ResponseEntity<String> save(MovieRequest movie);


    ResponseEntity<List<Movie>> getAll(int pageNo,int offset);

    ResponseEntity<List<Movie>> searchByTitle(String title, int pageNo, int pageSize);

    ResponseEntity<List<Movie>> searchByCategory(String category, int pageNo, int pageSize);

    ResponseEntity<String> updateMovie(long id,MovieRequest curMovie);

    ResponseEntity<String> deleteMovie(long id);
}
