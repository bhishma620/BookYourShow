package com.bhishma.bookyourshow.repo;

import com.bhishma.bookyourshow.entity.CinemaHall;
import com.bhishma.bookyourshow.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CinemaHallRepo extends JpaRepository<CinemaHall,Long> {

    Optional<CinemaHall>findByNameAndLocation(String name, String location);

    Page<CinemaHall>findByCity(String city, Pageable pageable);

}
