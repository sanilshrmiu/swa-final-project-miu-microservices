package com.example.emailservice.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GmailEmailSender {

    private final Configuration freemarkerConfig;
    private final JavaMailSender javaMailSender;

    public void sendEmail(String to,String subject, String message) throws IOException, TemplateException, MessagingException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("subject", subject);
        model.put("message", message);
        freemarkerConfig.getTemplate("emailTemplate.ftl").process(model, stringWriter);
        String content = stringWriter.getBuffer().toString();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setFrom("swafinalmicroservices@gmail.com");
        String emailContent = content;
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }


}
