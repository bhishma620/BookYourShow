package com.bhishma.bookyourshow.repo;

import com.bhishma.bookyourshow.entity.Theater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  TheaterRepo extends JpaRepository<Theater,Long> {

    Optional<Theater> findByCinemaHallIdAndName(long cinemaHallId, String name);

    Optional<Theater> findByCinemaHallIdAndId(long cinemaHallId, long theaterId);

    Page<Theater> findByCinemaHallId(long cinemaHallId, Pageable pageable);
}
