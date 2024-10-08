package com.bhishma.bookyourshow.repo;

import com.bhishma.bookyourshow.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovieRepo extends JpaRepository<Movie,Long> {


    List<Movie> findByTitleContaining(String title, Pageable pageable);

    List<Movie> findByType(String category, Pageable pageable);

    Optional<Movie> findByTitleAndReleaseDateAndLanguage(String title, Date releaseDate,String language);
}
