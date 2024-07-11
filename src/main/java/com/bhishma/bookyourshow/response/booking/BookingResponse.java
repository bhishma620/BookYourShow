package com.bhishma.bookyourshow.response.booking;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BookingResponse implements Serializable {
    private long bookingId;

    private String time;

    private HttpStatus status;
}
