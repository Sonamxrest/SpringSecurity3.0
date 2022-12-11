package com.xrest.nchl.controller;

import com.xrest.nchl.dto.TextMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("web")
public class WebSocketTextController {
    @Autowired
    public SimpMessagingTemplate template;


    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody TextMessageDto textMessageDTO) {
        template.convertAndSend("/topic/recieve/" + (textMessageDTO.getId() == 0 ? 1 : 0), textMessageDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @MessageMapping("/sendMessage")
//    public void receiveMessage(@Payload TextMessageDto textMessageDTO) {
//        System.out.println(textMessageDTO);
//    }
//
//
//    @SendTo("/topic/message")
//    public TextMessageDto broadcastMessage(@Payload TextMessageDto textMessageDTO) {
//        return textMessageDTO;
//    }
}
