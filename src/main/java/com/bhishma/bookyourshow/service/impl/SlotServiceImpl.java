package com.bhishma.bookyourshow.service.impl;

import com.bhishma.bookyourshow.entity.Slot;
import com.bhishma.bookyourshow.repo.SlotRepo;
import com.bhishma.bookyourshow.request.slot.SlotRequest;
import com.bhishma.bookyourshow.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;


@Service
public class SlotServiceImpl implements SlotService {
    @Autowired
    SlotRepo slotRepo;



    @Override
    public ResponseEntity<String> createSlot(SlotRequest slot) {

        long cinemaHallId = slot.getCinemaHallId();
        long movieId = slot.getMovieId();
        long theaterId = slot.getTheaterId();
        LocalDate date = slot.getDate();
        LocalTime startTime = slot.getStartTime();

        Boolean isPresent = slotRepo.findByCinemaHallIdAndMovieIdAndTheaterIdAndStartTimeAndDate(
               cinemaHallId, movieId, theaterId, startTime, date ).isPresent();


        if(!isPresent){
            Slot curSlot = Slot.builder()
                            .cinemaHallId(cinemaHallId)
                             .movieId(movieId)
                             .theaterId(theaterId)
                             .startTime(startTime)
                              .date(date).build();

            slotRepo.save(curSlot);
            return ResponseEntity.ok("Successfully Created");
        }

        return new ResponseEntity<>("Already Booked",HttpStatus.OK);

    }

}
