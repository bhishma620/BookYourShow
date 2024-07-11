package com.bhishma.bookyourshow.repo;

import com.bhishma.bookyourshow.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking,Long> {

    Optional<Booking> findByCinemaHallIdAndTheaterIdAndSlotIdAndTicketId(long cinemaHallId, long theaterId, long slotId, long ticketId);
}

