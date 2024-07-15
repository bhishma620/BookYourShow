package com.bhishma.bookyourshow.service.impl;

import com.bhishma.bookyourshow.entity.Slot;
import com.bhishma.bookyourshow.repo.SlotRepo;
import com.bhishma.bookyourshow.request.slot.SlotRequest;
import com.bhishma.bookyourshow.response.slot.SlotResponse;
import com.bhishma.bookyourshow.service.SlotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Service
public class SlotServiceImpl implements SlotService {
    @Autowired
    SlotRepo slotRepo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public ResponseEntity<String> createSlot(SlotRequest slot) {

        long cinemaHallId = slot.getCinemaHallId();
        long movieId = slot.getMovieId();
        long theaterId = slot.getTheaterId();
        LocalDate date = slot.getDate();
        LocalTime startTime = slot.getStartTime();



        Boolean isPresent = isPresent(cinemaHallId, movieId, theaterId, startTime, date);


        if (!isPresent) {
            Slot curSlot = Slot.builder()
                    .cinemaHallId(cinemaHallId)
                    .movieId(movieId)
                    .theaterId(theaterId)
                    .startTime(startTime)
                    .date(date).build();

            slotRepo.save(curSlot);
            return ResponseEntity.ok("Successfully Created");
        }

        return new ResponseEntity<>("Already Booked", HttpStatus.NOT_FOUND);

    }

    private boolean isPresent(long cinemaHallId, long movieId, long theaterId, LocalTime startTime, LocalDate date) {
        return slotRepo.findByCinemaHallIdAndMovieIdAndTheaterIdAndStartTimeAndDate(
                cinemaHallId, movieId, theaterId, startTime, date).isPresent();
    }

    @Override
    public ResponseEntity<String> updateSlot(long slotId, LocalTime time) {

      Optional<Slot> slot = slotRepo.findById(slotId);

      if(slot.isPresent()){

          Slot cur = slot.get();

          cur.setStartTime(time);

          slotRepo.save(cur);

          return ResponseEntity.ok("Successfully Updated");

      }

      return new ResponseEntity<>("No Slot Found", HttpStatus.NOT_FOUND);


    }

    @Override
    public ResponseEntity<List<SlotResponse>> getSlotByDateAndCinemaHallId(LocalDate date, long cinemaHallId) {

        List<SlotResponse> responses = slotRepo.findAllByDateAndCinemaHallId(date, cinemaHallId).
                stream().map(slot ->{
                    return modelMapper.map(slot, SlotResponse.class);
                }).toList();

        return ResponseEntity.ok(responses);

    }

}
