package com.example.emailservice.service.impl;

import com.example.emailservice.dto.KafkaMessageTemplate;
import com.example.emailservice.service.EmailSenderService;
import com.example.emailservice.service.GmailEmailSender;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.emailservice.constants.EmailConstants.EMAIL_MESSAGE;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final GmailEmailSender gmailEmailSender;

    @Override
    public void sendMessage(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        Gson gson = new Gson();
        KafkaMessageTemplate kafkaMessageTemplate = gson.fromJson(message, KafkaMessageTemplate.class);
        String emailContent = String.format(EMAIL_MESSAGE, kafkaMessageTemplate.getCreatedUserType(),kafkaMessageTemplate.getEmail(),formattedDate);

        log.info(emailContent);

        gmailEmailSender.sendEmail(kafkaMessageTemplate.getEmail(),kafkaMessageTemplate.getCreatedUserType()+" Created", emailContent);

    }
}
