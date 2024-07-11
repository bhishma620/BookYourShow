package com.bhishma.bookyourshow.response.booking;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class CheckStatus implements Serializable {
    private String response;
    private HttpStatus status;
}
