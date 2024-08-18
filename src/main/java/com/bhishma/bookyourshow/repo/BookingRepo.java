package com.bhishma.bookyourshow.repo;

import com.bhishma.bookyourshow.entity.Booking;
import com.bhishma.bookyourshow.response.booking.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking,Long> {

    Optional<Booking> findByCinemaHallIdAndTheaterIdAndSlotIdAndSeatIdAndUserId(long cinemaHallId, long theaterId,
                                                                         long slotId, long seatId,
                                                                         long userId );

    Optional<Booking> findByCinemaHallIdAndTheaterIdAndSlotIdAndSeatId(long cinemaHallId, long theaterId, long slotId, long seatId);

    @Modifying
    @Transactional
    @Query("UPDATE Booking b SET b.time = :time, b.userId = :userId, b.status = :status WHERE b.slotId = :slotId AND b.seatId = :seatId")
    int updateUserAndStatus(@Param("slotId") long slotId, @Param("seatId") long seatId, @Param("time") String time,
                            @Param("userId") long userId,@Param("status") int status);
    @Modifying
    @Transactional
    @Query("UPDATE Booking b SET b.time = :time WHERE b.slotId = :slotId AND b.seatId = :seatId")
    int updateBySlotIdAndTicketId(@Param("slotId") long slotId, @Param("seatId") long seatId, @Param("time") String time);



        @Query(value = "SELECT seat_id AS seatId, " +
                "CASE " +
                "WHEN status = 0 AND ABS(TIMESTAMPDIFF(MINUTE, time, NOW())) < :waitTime THEN -1 " +
                "ELSE status END AS status " +
                "FROM booking " +
                "WHERE slot_id = :slotId", nativeQuery = true)
        List<Object[]> findRawSeatStatusBySlotId(@Param("slotId") long slotId,
                                                 @Param("waitTime") int waitTime);

         default List<SeatStatus> getSeatStatusBySlotId(long slotId, int waitTime) {
            List<Object[]> rawResults = findRawSeatStatusBySlotId(slotId, waitTime);
            return rawResults.stream()
                    .map(result -> new SeatStatus(((Number) result[0]).longValue(), ((Number) result[1]).intValue()))
                    .toList();
        }



}

