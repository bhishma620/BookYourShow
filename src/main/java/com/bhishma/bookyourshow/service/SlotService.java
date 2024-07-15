package com.bhishma.bookyourshow.service;

import com.bhishma.bookyourshow.request.slot.SlotRequest;
import com.bhishma.bookyourshow.response.slot.SlotResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SlotService {
    ResponseEntity<String> createSlot(SlotRequest slot);

    ResponseEntity<String> updateSlot(long slotId, LocalTime time);

    ResponseEntity<List<SlotResponse>> getSlotByDateAndCinemaHallId(LocalDate date, long cinemaHallId);

    ResponseEntity<String> deleteSlot(long id);
}
