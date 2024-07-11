package com.bhishma.bookyourshow.controller;

import com.bhishma.bookyourshow.request.booking.BookingRequest;
import com.bhishma.bookyourshow.response.booking.BookingResponse;
import com.bhishma.bookyourshow.service.BookingService;
import com.bhishma.bookyourshow.service.impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}

