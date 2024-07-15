package com.bhishma.bookyourshow.repo;


import com.bhishma.bookyourshow.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface SlotRepo extends JpaRepository<Slot,Long>{

    Optional<Slot> findByCinemaHallIdAndMovieIdAndTheaterIdAndStartTimeAndDate( long cinemaHallId, long movieId,
                                                                               long theaterId, LocalTime startTime,
                                                                               LocalDate date);
}
