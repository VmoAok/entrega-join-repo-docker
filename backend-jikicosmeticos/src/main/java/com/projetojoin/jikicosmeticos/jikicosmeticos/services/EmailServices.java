package com.projetojoin.jikicosmeticos.jikicosmeticos.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServices {
    private final JavaMailSender javaMailSender;

    public EmailServices(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(SimpleMailMessage message) {
        javaMailSender.send(message);
    }
}