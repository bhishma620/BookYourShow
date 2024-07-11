package com.bhishma.bookyourshow.request.booking;

import lombok.Data;

@Data
public class BookingRequest {
    private long cinemaHallId;

    private long theaterId;

    private long slotId;

    private long ticketId;
}
