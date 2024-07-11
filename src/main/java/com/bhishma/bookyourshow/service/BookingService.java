package com.bhishma.bookyourshow.service;

import com.bhishma.bookyourshow.request.booking.BookingRequest;
import com.bhishma.bookyourshow.response.booking.BookingResponse;

public interface BookingService {


    BookingResponse bookTicket(BookingRequest bookingRequest);
}
