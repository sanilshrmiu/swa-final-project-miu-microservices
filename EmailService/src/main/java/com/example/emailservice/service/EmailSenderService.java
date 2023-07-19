package com.example.emailservice.service;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailSenderService {

    void sendMessage(String message) throws TemplateException, IOException, MessagingException;

}
