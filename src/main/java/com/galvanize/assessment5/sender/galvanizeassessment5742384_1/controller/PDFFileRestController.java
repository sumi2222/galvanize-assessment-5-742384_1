package com.galvanize.assessment5.sender.galvanizeassessment5742384_1.controller;

import com.galvanize.assessment5.sender.galvanizeassessment5742384_1.entity.FrontEndEntity;
import com.galvanize.assessment5.sender.galvanizeassessment5742384_1.service.RabbitMQSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sender")
public class PDFFileRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PDFFileRestController.class);
    private final RabbitMQSenderService mqSenderService;

    @Autowired
    public PDFFileRestController(RabbitMQSenderService mqSenderService) {
        this.mqSenderService = mqSenderService;
    }

    @PutMapping("/file")
    public ResponseEntity<String> processPDFFile(@RequestBody FrontEndEntity frontEndEntity) {
        LOGGER.info("Rest controller has been called, path variable is :: {} ",frontEndEntity.toString());
        String response = mqSenderService.processPDFFile(frontEndEntity);
        if(response!=null) { return ResponseEntity.ok(response); }
        else{ return ResponseEntity.unprocessableEntity().build();}
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        LOGGER.info("Rest controller has been called");
        return ResponseEntity.ok("I am here........");
    }
}
