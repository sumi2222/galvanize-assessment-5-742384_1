package com.galvanize.assessment5.sender.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RESTCallToReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(RESTCallToReceiver.class);

    @Value("${reception.endpoint.url}")
    private String responseURL;

    public String getResponseBack() {
            RestTemplate restTemplate = new RestTemplate();
            try {
                String url = responseURL;
                restTemplate.getForEntity(url, String.class);
                return " Message received  ";
            } catch (Exception e) {
                String.format(" Rest call Exception :: %s ", e);
            }
        return "Response is not found. ";
        }

    }

