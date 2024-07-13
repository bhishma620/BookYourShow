package com.bhishma.bookyourshow.response.booking;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatStatus {

    long seatId;

    int status;
}
