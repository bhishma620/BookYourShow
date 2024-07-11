package com.bhishma.bookyourshow.service.impl;

import com.bhishma.bookyourshow.config.BookingCacheKey;
import com.bhishma.bookyourshow.entity.Booking;
import com.bhishma.bookyourshow.repo.BookingRepo;
import com.bhishma.bookyourshow.response.booking.BookingResponse;
import com.bhishma.bookyourshow.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepo bookingRepo;



    @Autowired
    ModelMapper modelMapper;

    private static final ThreadLocal<Boolean> fromCache = ThreadLocal.withInitial(() -> false);

    @Override
    @Cacheable(cacheNames = "BookingResponse",keyGenerator = "bookingCacheKey")
    public BookingResponse bookTicket(long cinemaHallId, long theaterId, long slotId, long ticketId) {

        BookingResponse response = new BookingResponse();

        Optional<Booking> booking = bookingRepo.
                findByCinemaHallIdAndTheaterIdAndSlotIdAndTicketId(cinemaHallId,theaterId,slotId,ticketId);

        if(booking.isPresent()){
            response.setStatus(HttpStatus.CONFLICT);
        }
        else{
            Booking booking1 = new Booking();
            booking1.setTicketId(ticketId);
            booking1.setTheaterId(theaterId);
            booking1.setCinemaHallId(cinemaHallId);
            booking1.setSlotId(slotId);
            booking1.setTime(LocalDateTime.now());

            Booking saved =bookingRepo.save(booking1);

            response.setBookingId(saved.getBookingId());
            response.setTime(saved.getTime());
            response.setStatus(HttpStatus.OK);
        }
        fromCache.set(true);


        return response;

    }
    public boolean isFromDb() {
        boolean cacheStatus = fromCache.get();
        fromCache.remove();
        return cacheStatus;
    }
}
