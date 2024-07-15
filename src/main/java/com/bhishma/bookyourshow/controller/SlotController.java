package com.bhishma.bookyourshow.controller;

import com.bhishma.bookyourshow.entity.Slot;
import com.bhishma.bookyourshow.request.slot.SlotRequest;
import com.bhishma.bookyourshow.response.slot.SlotResponse;
import com.bhishma.bookyourshow.service.SlotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("v1/slot")
public class SlotController {


    @Autowired
    SlotService slotService;

    @PostMapping()
    ResponseEntity<String> createSlot(@Valid @RequestBody SlotRequest slot){

       return slotService.createSlot(slot);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> updateSlot(@PathVariable("id") long slotId, @RequestParam("time") LocalTime time){
        return slotService.updateSlot(slotId, time);
    }



}
