package com.xrest.nchl.dto;

import lombok.Data;

@Data
public class TextMessageDto {

    private int id;
    private String message;
    private boolean sender;

}
