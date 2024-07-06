package com.bhishma.bookyourshow.service.impl;

import com.bhishma.bookyourshow.entity.Theater;
import com.bhishma.bookyourshow.repo.TheaterRepo;
import com.bhishma.bookyourshow.request.theater.TheaterRequest;
import com.bhishma.bookyourshow.response.theater.TheaterResponse;
import com.bhishma.bookyourshow.service.TheaterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterServiceImpl implements TheaterService {

    @Autowired
    TheaterRepo theaterRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> add(TheaterRequest theater) {
        boolean isPresent =
                theaterRepo.findByCinemaHallIdAndName(theater.getCinemaHallId(), theater.getName()).isPresent();

        if (isPresent) {
            return new ResponseEntity<>("Already Exists", HttpStatus.CONFLICT);
        } else {
            Theater theater1 = modelMapper.map(theater, Theater.class);
            theaterRepo.save(theater1);
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<TheaterResponse> getTheaterDetailsByCinemaHallIdAndTheaterId(long cinemaHallId, long theaterId) {

        Optional<Theater> theaterDetails = theaterRepo.findByCinemaHallIdAndId(cinemaHallId, theaterId);

        if (theaterDetails.isPresent()) {
            TheaterResponse res = modelMapper.map(theaterDetails.get(), TheaterResponse.class);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<TheaterResponse>> getAllByCinemaHallId(long cinemaHallId, int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Theater> theaterPage = theaterRepo.findByCinemaHallId(cinemaHallId, pageable);

        List<TheaterResponse> responses = theaterPage.getContent().stream().map(theater -> {
            return modelMapper.map(theater, TheaterResponse.class);
        }).toList();

        return new ResponseEntity<>(responses,HttpStatus.OK);

    }
}
