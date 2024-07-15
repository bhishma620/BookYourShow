package com.bhishma.bookyourshow.controller;

import com.bhishma.bookyourshow.entity.Slot;
import com.bhishma.bookyourshow.request.slot.SlotRequest;
import com.bhishma.bookyourshow.response.slot.SlotResponse;
import com.bhishma.bookyourshow.service.SlotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/slot")
public class SlotController {


    @Autowired
    SlotService slotService;

    @PostMapping()
    ResponseEntity<String> createSlot(@Valid @RequestBody SlotRequest slot){

       return slotService.createSlot(slot);
    }


}
