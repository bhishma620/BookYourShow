package com.bhishma.bookyourshow.service.impl;

import com.bhishma.bookyourshow.entity.Movie;
import com.bhishma.bookyourshow.repo.MovieRepo;
import com.bhishma.bookyourshow.request.movie.MovieRequest;
import com.bhishma.bookyourshow.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepo movieRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> save(MovieRequest movieRequest) {

        Movie movie = modelMapper.map(movieRequest, Movie.class);

        try {
            movieRepo.save(movie);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed To Saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("ADDED Successfully", HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<List<Movie>> getAll() {

        List<Movie> movies = movieRepo.findAll();
        return new ResponseEntity<>(movies, HttpStatus.OK);


    }

}
