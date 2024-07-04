package com.bhishma.bookyourshow.service.impl;

import com.bhishma.bookyourshow.entity.CinemaHall;
import com.bhishma.bookyourshow.repo.CinemaHallRepo;
import com.bhishma.bookyourshow.request.cinemahall.CinemaHallRequest;
import com.bhishma.bookyourshow.service.CinemaHallService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {

    @Autowired
    CinemaHallRepo cinemaHallRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> addCinemaHall(CinemaHallRequest cinemaHall) {

        boolean isPresent = cinemaHallRepo.
                findByNameAndLocation(cinemaHall.getName(), cinemaHall.getLocation()).isPresent();

        if(isPresent)return new ResponseEntity<>("Already Exists", HttpStatus.CONFLICT);
        else{
            CinemaHall hall=modelMapper.map(cinemaHall,CinemaHall.class);
            cinemaHallRepo.save(hall);
            return new ResponseEntity<>("Saved",HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<List<CinemaHall>> getCinemaHallByCity(String city, int pageNo, int pageSize) {

        Pageable pageable=  PageRequest.of(pageNo,pageSize);

       List<CinemaHall> cinemaHalls= cinemaHallRepo.findByCity(city,pageable).getContent();

       return new ResponseEntity<>(cinemaHalls,HttpStatus.OK);


    }
}
