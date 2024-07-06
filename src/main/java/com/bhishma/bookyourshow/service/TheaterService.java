package com.bhishma.bookyourshow.service;

import com.bhishma.bookyourshow.request.theater.TheaterRequest;
import com.bhishma.bookyourshow.response.theater.TheaterResponse;
import org.springframework.http.ResponseEntity;

public interface TheaterService {
   ResponseEntity<String> add(TheaterRequest theater);

   ResponseEntity<TheaterResponse> getTheaterDetailsByCinemaHallIdAndTheaterId(long cinemaHallId, long theaterId);
}
