package com.bhishma.bookyourshow.controller;

import com.bhishma.bookyourshow.request.booking.BookingRequest;
import com.bhishma.bookyourshow.response.booking.BookingResponse;
import com.bhishma.bookyourshow.response.booking.CheckStatus;
import com.bhishma.bookyourshow.response.booking.SeatStatus;
import com.bhishma.bookyourshow.service.BookingService;
import com.bhishma.bookyourshow.service.impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("")
    ResponseEntity<BookingResponse> bookTicket(@RequestBody BookingRequest bookingDetails){

        BookingResponse bookingResponse = bookingService.bookTicket(bookingDetails);

       return ResponseEntity.ok(bookingResponse);
    }


    @PostMapping("/check")
    ResponseEntity<CheckStatus> checkTicketStatus(@RequestBody BookingRequest bookingDetails){

        CheckStatus response = bookingService.checkTicketStatus(bookingDetails.getCinemaHallId(),
                bookingDetails.getTheaterId(),bookingDetails.getSlotId(),bookingDetails.getTicketId());

        boolean fromDb = new BookingServiceImpl().isFromDb();

        if(fromDb){
            return ResponseEntity.ok()
                    .header("X-data-Source","DB")
                    .body(response);
        }
        else{
           if(response.getStatus()== HttpStatus.OK){
               response.setResponse("Booking On-Going");
           }
            return ResponseEntity.ok()
                    .header("X-data-Source","Cache")
                    .body(response);
        }

    }

    @GetMapping("/status/{slotId}")
    List<SeatStatus> getSeatStatusBySlotId(@PathVariable("slotId") long slotId){

        return bookingService.getSeatStatusBySlotId(slotId);
    }




}

