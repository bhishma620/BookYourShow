package com.bhishma.bookyourshow.service;

import com.bhishma.bookyourshow.request.theater.TheaterRequest;
import com.bhishma.bookyourshow.response.theater.TheaterResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TheaterService {
   ResponseEntity<String> add(TheaterRequest theater);

   ResponseEntity<TheaterResponse> getTheaterDetailsByCinemaHallIdAndTheaterId(long cinemaHallId, long theaterId);

    ResponseEntity<List<TheaterResponse>> getAllByCinemaHallId(long cinemaHallId,int pageNo, int pageSize);

    ResponseEntity<String> update(long id, TheaterRequest theaterRequest);

    ResponseEntity<String> delete(long id);
}
