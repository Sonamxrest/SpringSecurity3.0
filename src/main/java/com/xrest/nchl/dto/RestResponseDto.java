package com.xrest.nchl.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class RestResponseDto {

    public ResponseEntity<?> successModel(Object obj) {
        return  new ResponseEntity(obj,HttpStatus.OK);
    }
}
