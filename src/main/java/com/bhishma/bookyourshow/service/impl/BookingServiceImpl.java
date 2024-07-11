package com.bhishma.bookyourshow.service.impl;

import com.bhishma.bookyourshow.config.BookingCacheKey;
import com.bhishma.bookyourshow.entity.Booking;
import com.bhishma.bookyourshow.repo.BookingRepo;
import com.bhishma.bookyourshow.request.booking.BookingRequest;
import com.bhishma.bookyourshow.response.booking.BookingResponse;
import com.bhishma.bookyourshow.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public BookingResponse bookTicket(BookingRequest bookingRequest) {

        BookingResponse response = new BookingResponse();
        System.out.println(bookingRequest.getCinemaHallId());

        Optional<Booking> booking = bookingRepo.
                findByCinemaHallIdAndTheaterIdAndSlotIdAndTicketIdAndUserId(bookingRequest.getCinemaHallId(),
                        bookingRequest.getTheaterId(),bookingRequest.getSlotId(),bookingRequest.getTicketId(),
                        bookingRequest.getUserId());

        if(booking.isPresent()){
            response.setStatus(HttpStatus.CONFLICT);
        }
        else{
//            Booking bookingDetails = modelMapper.map(bookingRequest,Booking.class);
            Booking bookingDetails = new Booking();

            bookingDetails.setTicketId(bookingRequest.getTicketId());
            bookingDetails.setSlotId(bookingRequest.getSlotId());
            bookingDetails.setTheaterId(bookingRequest.getTheaterId());
            bookingDetails.setUserId(bookingRequest.getUserId());
            bookingDetails.setCinemaHallId(bookingRequest.getCinemaHallId());

            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedNow = now.format(formatter);

            bookingDetails.setTime(formattedNow);

            System.out.println(bookingDetails);


            Booking saved =bookingRepo.save(bookingDetails);

            response.setBookingId(saved.getBookingId());
            response.setTime(saved.getTime());
            response.setStatus(HttpStatus.OK);
        }
        return response;

    }
    public boolean isFromDb() {
        boolean cacheStatus = fromCache.get();
        fromCache.remove();
        return cacheStatus;
    }


}
