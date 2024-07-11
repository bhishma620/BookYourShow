package com.bhishma.bookyourshow.service;

import com.bhishma.bookyourshow.response.booking.BookingResponse;

public interface BookingService {


    BookingResponse bookTicket(long cinemaHallId, long theaterId, long slotId, long ticketId);
}
