package com.bhishma.bookyourshow.service.impl;

import com.bhishma.bookyourshow.config.BookingCacheKey;
import com.bhishma.bookyourshow.entity.Booking;
import com.bhishma.bookyourshow.repo.BookingRepo;
import com.bhishma.bookyourshow.request.booking.BookingRequest;
import com.bhishma.bookyourshow.response.booking.BookingResponse;
import com.bhishma.bookyourshow.response.booking.CheckStatus;
import com.bhishma.bookyourshow.response.booking.SeatStatus;
import com.bhishma.bookyourshow.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepo bookingRepo;

    @Value("${booking.wait-time}")
    private int waitTime;



    @Autowired
    ModelMapper modelMapper;

    private static final ThreadLocal<Boolean> fromCache = ThreadLocal.withInitial(() -> false);

    @Override
    public BookingResponse bookTicket(BookingRequest bookingRequest) {

        BookingResponse response = new BookingResponse();

        Optional<Booking> booking = bookingRepo.
                findByCinemaHallIdAndTheaterIdAndSlotIdAndTicketId(bookingRequest.getCinemaHallId(),
                        bookingRequest.getTheaterId(),bookingRequest.getSlotId(),bookingRequest.getTicketId());

        if(booking.isPresent()) {
            if (booking.get().getStatus() == 1) {
                response.setStatus(HttpStatus.CONFLICT);
            } else {

                LocalDateTime now = LocalDateTime.now();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedNow = now.format(formatter);

                bookingRepo.updateUserAndStatus(bookingRequest.getSlotId(),
                        bookingRequest.getTicketId(), formattedNow, bookingRequest.getUserId(), 1);

                response.setBookingId(booking.get().getBookingId());
                response.setTime(formattedNow);

                response.setStatus(HttpStatus.OK);
            }
        }
        return response;

    }

    @Override
    @Cacheable(cacheNames = "CheckStatus" ,keyGenerator = "bookingCacheKey")
    public CheckStatus checkTicketStatus(long cinemaHallId, long theaterId, long slotId, long ticketId) {

        fromCache.set(true);

        CheckStatus response = new CheckStatus();

        Optional<Booking> booking = bookingRepo.findByCinemaHallIdAndTheaterIdAndSlotIdAndTicketId(cinemaHallId,
                theaterId,slotId,ticketId);

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);


        if(booking.isPresent()) {

            if (booking.get().getStatus() == 1) {
                response.setResponse("Already Booked");
                response.setStatus(HttpStatus.CONFLICT);

            } else {
                bookingRepo.updateBySlotIdAndTicketId(slotId,ticketId,formattedNow);
//                System.out.println(formattedNow+" "+slotId+" "+ticketId);
                response.setResponse("Good to GO");
                response.setStatus(HttpStatus.OK);

            }
        }
        else{
            Booking booking1 = new Booking();

            booking1.setTicketId(ticketId);
            booking1.setSlotId(slotId);
            booking1.setTheaterId(theaterId);
            booking1.setCinemaHallId(cinemaHallId);


            booking1.setTime(formattedNow);


            bookingRepo.save(booking1);

            response.setResponse("Good to GO");
            response.setStatus(HttpStatus.OK);

        }


        return response;


    }

    @Override
    public List<SeatStatus> getSeatStatusBySlotId(long slotId) {



        List<SeatStatus> response = bookingRepo.getSeatStatusBySlotId(slotId,waitTime);

        return response;
    }

    public boolean isFromDb() {
        boolean cacheStatus = fromCache.get();
        fromCache.remove();
        return cacheStatus;
    }


}
