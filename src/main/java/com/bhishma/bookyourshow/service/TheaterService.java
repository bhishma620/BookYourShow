package com.bhishma.bookyourshow.service;

import com.bhishma.bookyourshow.request.theater.TheaterRequest;
import org.springframework.http.ResponseEntity;

public interface TheaterService {
   ResponseEntity<String> add(TheaterRequest theater);

}
