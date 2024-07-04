package com.bhishma.bookyourshow.service.impl;

import com.bhishma.bookyourshow.entity.Theater;
import com.bhishma.bookyourshow.repo.TheaterRepo;
import com.bhishma.bookyourshow.request.theater.TheaterRequest;
import com.bhishma.bookyourshow.service.TheaterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TheaterServiceImpl implements TheaterService {

    @Autowired
    TheaterRepo theaterRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> add(TheaterRequest theater) {
        boolean isPresent=
                theaterRepo.findByCinemaHallIdAndName(theater.getCinemaHallId(),theater.getName()).isPresent();

        if(isPresent){
            return new ResponseEntity<>("Already Exists", HttpStatus.CONFLICT);
        }
        else{
            Theater theater1=modelMapper.map(theater,Theater.class);
            theaterRepo.save(theater1);
            return new ResponseEntity<>("Saved",HttpStatus.OK);
        }
    }
}
