package com.jpcchaves.adotar.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EmailServiceImpl {

    private final JavaMailSender mailSender;
    Logger log = Logger.getLogger("Email");
    @Value("${mail.username}")
    private String to;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }

    public void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Teste");
        message.setText("sdfsdfdsfdsfds");
        message.setTo(to);

        mailSender.send(message);
    }
}
