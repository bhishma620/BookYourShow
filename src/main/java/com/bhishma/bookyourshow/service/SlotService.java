package com.bhishma.bookyourshow.service;

import com.bhishma.bookyourshow.request.slot.SlotRequest;
import com.bhishma.bookyourshow.response.slot.SlotResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;

public interface SlotService {
    ResponseEntity<String> createSlot(SlotRequest slot);

    ResponseEntity<String> updateSlot(long slotId, LocalTime time);
}
