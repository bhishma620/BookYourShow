package com.bhishma.bookyourshow.service.impl;

import com.bhishma.bookyourshow.entity.Movie;
import com.bhishma.bookyourshow.repo.MovieRepo;
import com.bhishma.bookyourshow.request.movie.MovieRequest;
import com.bhishma.bookyourshow.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepo movieRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> save(MovieRequest movieRequest) {


        Optional<Movie> oldMovie = movieRepo.
                findByTitleAndReleaseDateAndLanguage(movieRequest.getTitle(), movieRequest.getReleaseDate(), movieRequest.getLanguage());

        //If already in DB
        if (oldMovie.isPresent()){
            return new ResponseEntity<>("Already Exists", HttpStatus.CONFLICT);
        }
        else {

            Movie movie = modelMapper.map(movieRequest, Movie.class);

            try {
                movieRepo.save(movie);
            } catch (Exception e) {
                return new ResponseEntity<>("Failed To Saved", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>("ADDED Successfully", HttpStatus.CREATED);
        }
    }


    @Override
    public ResponseEntity<List<Movie>> getAll(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Movie> movies = movieRepo.findAll(pageable);

        return new ResponseEntity<>(movies.getContent(), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<Movie>> searchByTitle(String title, int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Movie> movies = movieRepo.findByTitleContaining(title, pageable);

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Movie>> searchByCategory(String category, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Movie> movies = movieRepo.findByType(category, pageable);

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateMovie(long id,MovieRequest curMovie) {

        boolean isPresent=movieRepo.findById(id).isPresent();

        if(isPresent) {
            Movie movie = modelMapper.map(curMovie, Movie.class);
            movie.setId(id);

            movieRepo.save(movie);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No Movie  Found with id "+id,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> deleteMovie(long id) {

        boolean isPresent=movieRepo.findById(id).isPresent();

        if(isPresent) {
            movieRepo.deleteById(id);
            return ResponseEntity.ok("Deleted Succcessfully");
        }
        else {
            return new ResponseEntity<>("No Movie  Found with id " + id, HttpStatus.NOT_FOUND);
        }

    }

}
