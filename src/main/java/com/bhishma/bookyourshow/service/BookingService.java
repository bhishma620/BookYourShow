package com.bhishma.bookyourshow.service;

import com.bhishma.bookyourshow.request.booking.BookingRequest;
import com.bhishma.bookyourshow.response.booking.BookingResponse;
import com.bhishma.bookyourshow.response.booking.CheckStatus;
import com.bhishma.bookyourshow.response.booking.SeatStatus;

import java.util.List;

public interface BookingService {


    BookingResponse bookTicket(BookingRequest bookingRequest);

    CheckStatus checkTicketStatus(long cinemaHallId, long theaterId, long slotId, long seatId);

    List<SeatStatus> getSeatStatusBySlotId(long slotId);
}
