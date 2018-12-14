package com.galvanize.assessment5.sender.controller;

import com.galvanize.assessment5.sender.service.MQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/sendmessage")
public class SenderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SenderController.class);
    private final MQService mqService;

    @Autowired
    public SenderController(MQService mqService) {
        this.mqService = mqService;
    }


    @GetMapping("/")
    public ResponseEntity<String> getPersonByPhone(@PathVariable String phoneNumber) {
        String messageSend= mqService.sendMessage("FAKE_PATH_TO_PDF_FILE");
        if (messageSend != null)
            return ResponseEntity.ok().body(messageSend);
        else return ResponseEntity.notFound().build();
    }


    //************************** REST Call To receiver ***************************

   /* @GetMapping("/getResponseBack")
    public ResponseEntity<String> visitorCheckout() {
    }*/
}
