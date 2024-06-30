package com.bhishma.bookyourshow.repo;

import com.bhishma.bookyourshow.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie,Long> {


    List<Movie> findByTitleContaining(String title, Pageable pageable);

    List<Movie> findByType(String category, Pageable pageable);
}
