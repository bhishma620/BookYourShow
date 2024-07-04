package com.bhishma.bookyourshow.service.impl;

import com.bhishma.bookyourshow.entity.CinemaHall;
import com.bhishma.bookyourshow.entity.Movie;
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

        if (isPresent) return new ResponseEntity<>("Already Exists", HttpStatus.CONFLICT);
        else {
            CinemaHall hall = modelMapper.map(cinemaHall, CinemaHall.class);
            cinemaHallRepo.save(hall);
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<List<CinemaHall>> getCinemaHallByCity(String city, int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        List<CinemaHall> cinemaHalls = cinemaHallRepo.findByCity(city, pageable).getContent();

        return new ResponseEntity<>(cinemaHalls, HttpStatus.OK);


    }

    @Override
    public ResponseEntity<List<CinemaHall>> getCinemaHallByName(String name, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        List<CinemaHall> cinemaHalls = cinemaHallRepo.findByNameContaining(name, pageable).getContent();

        return new ResponseEntity<>(cinemaHalls, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateCinemaHall(long id, CinemaHallRequest cinemaHall) {

        boolean isPresent = cinemaHallRepo.findById(id).isPresent();
        if (isPresent) {
            boolean exits = cinemaHallRepo.findByNameAndLocation(cinemaHall.getName(), cinemaHall.getLocation()).isPresent();
            if (exits) {
               return new ResponseEntity<>("Already Exits", HttpStatus.CONFLICT);
            }
            else {

                CinemaHall cinemaHall1 = modelMapper.map(cinemaHall, CinemaHall.class);
                cinemaHall1.setId(id);
                cinemaHallRepo.save(cinemaHall1);
                return new ResponseEntity<>("Updated", HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }

    }
}
