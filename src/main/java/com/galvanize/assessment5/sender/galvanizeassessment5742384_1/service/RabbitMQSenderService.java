package com.galvanize.assessment5.sender.galvanizeassessment5742384_1.service;

import com.galvanize.assessment5.sender.galvanizeassessment5742384_1.entity.FrontEndEntity;
import com.galvanize.assessment5.sender.galvanizeassessment5742384_1.entity.RabbitEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;

@Service
public class RabbitMQSenderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQSenderService.class);

    @Value("${amqp.exchange.name}")
    String appExchangeName;

    @Value("${amqp.routing.key}")
    String appRoutingKey;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String processPDFFile(FrontEndEntity frontEndEntity ) {
        String[] fpath = frontEndEntity.getPath();
        String buildPath =fpath[0].trim();
        try {
            for (int i = 1; i < fpath.length; i++) {
                buildPath = buildPath + fpath[i].trim();
                LOGGER.info("The absolute path is ==== " + buildPath);
            }
            ArrayList<String> linesOfTheFile = processFile(buildPath);
            if(linesOfTheFile==null) return null;
            linesOfTheFile.forEach(line -> {
                RabbitEntity entity = new RabbitEntity();
                LOGGER.info("The individual line of the file is :: " + line);

                entity.setText(line);
                LOGGER.info("The rabbitMQ entity is ==== " + entity.toString());
                sendMessage(appExchangeName, appRoutingKey, entity);
            });
            return "File sent ; line by line to the rabbit queue";
        }catch(Exception e){
            LOGGER.info("An exception occurred :: {}", e);
            return null;
        }
    }

    public void sendMessage(String exchange, String routingKey, Object data) {
        if (data == null) {
            data = new RabbitEntity();
            ((RabbitEntity) data).setText(" RabbitMq Message  :: Hello, could not parse the file ================ ");
        }
        LOGGER.info("Sending message to the queue using routingKey {}. Message= {}", routingKey, data);
        rabbitTemplate.convertAndSend(exchange, routingKey, data);
        LOGGER.info("The message has been sent to the queue.");
    }

    public ArrayList<String> processFile(String filePath) {
        // File file  = new File ("C://test1/subtest/text1.txt");
        File file = new File(filePath);
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line1;
            line1 = bufferReader.readLine();
            while (line1 != null) {
                arrayList.add(line1.trim());
                line1 = bufferReader.readLine();
            }
            LOGGER.info("The entire file is ==== " + arrayList.toString());
            return arrayList;
        }catch (Exception e) {
            LOGGER.info("An exception occurred :: {}", e);
            return null;
        }
    }
}
