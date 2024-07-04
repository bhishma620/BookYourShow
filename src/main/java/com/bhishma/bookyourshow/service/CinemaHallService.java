package com.bhishma.bookyourshow.service;

import com.bhishma.bookyourshow.entity.CinemaHall;
import com.bhishma.bookyourshow.request.cinemahall.CinemaHallRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CinemaHallService {

    ResponseEntity<String> addCinemaHall(CinemaHallRequest cinemaHall);

    ResponseEntity<List<CinemaHall>> getCinemaHallByCity(String city, int pageNo, int pageSize);

    ResponseEntity<List<CinemaHall>> getCinemaHallByName(String name, int pageNo, int pageSize);
}
