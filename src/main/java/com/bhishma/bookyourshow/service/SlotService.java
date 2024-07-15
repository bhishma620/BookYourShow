package com.bhishma.bookyourshow.service;

import com.bhishma.bookyourshow.request.slot.SlotRequest;
import com.bhishma.bookyourshow.response.slot.SlotResponse;
import org.springframework.http.ResponseEntity;

public interface SlotService {
    ResponseEntity<String> createSlot(SlotRequest slot);
}
