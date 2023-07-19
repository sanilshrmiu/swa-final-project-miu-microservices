package com.example.emailservice.receiver;

import com.example.emailservice.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaReceiver {

    private final EmailSenderService service;

    @KafkaListener(topics = {"email-topic"})
    public void receive(@Payload String message) {
        log.info("Receiver received message= "+ message);
        service.sendMessage(message);
    }

}
