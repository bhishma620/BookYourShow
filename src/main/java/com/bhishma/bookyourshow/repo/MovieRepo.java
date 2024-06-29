package com.bhishma.bookyourshow.repo;

import com.bhishma.bookyourshow.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie,Long> {
}
