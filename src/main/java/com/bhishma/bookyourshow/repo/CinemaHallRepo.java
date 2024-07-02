package com.bhishma.bookyourshow.repo;

import com.bhishma.bookyourshow.entity.CinemaHall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CinemaHallRepo extends JpaRepository<CinemaHall,Long> {

    Optional<CinemaHall>findByNameAndLocation(String name, String location);
}
