package com.example.emailservice.receiver;

import com.example.emailservice.service.EmailSenderService;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaReceiver {

    private final EmailSenderService service;

    @KafkaListener(topics = {"email-topic"})
    public void receive(@Payload String message) throws TemplateException, IOException, MessagingException {
        log.info("Receiver received message= "+ message);
        service.sendMessage(message);
    }

}
