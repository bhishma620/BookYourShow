package com.bhishma.bookyourshow.service;

import com.bhishma.bookyourshow.request.cinemahall.CinemaHallRequest;
import org.springframework.http.ResponseEntity;

public interface CinemaHallService {

    ResponseEntity<String> addCinemaHall(CinemaHallRequest cinemaHall);
}
