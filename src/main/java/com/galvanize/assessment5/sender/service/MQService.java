package com.galvanize.assessment5.sender.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MQService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MQService.class);
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RESTCallToReceiver rESTCallToReceiver;

    @Value("${amqp.exchange.name}")
    String appExchangeName;

    @Value("${amqp.routing.key}")
    String appRoutingKey;

    String PDF_file = "FAKE-PATH-TO-PDF-FILE";



    public String sendMessage(Object PDF_file) {
        LOGGER.info("Sending message to the queue using routingKey {}. Message= {}", appRoutingKey, PDF_file);
        rabbitTemplate.convertAndSend(appExchangeName, appRoutingKey, PDF_file);
        LOGGER.info("The message has been sent to the queue.");
        return "Message send ....";
    }



}
