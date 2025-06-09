package com.projetojoin.jikicosmeticos.jikicosmeticos.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServicesTest {

    private JavaMailSender javaMailSender;
    private EmailServices emailServices;

    @BeforeEach
    void setUp() {
        javaMailSender = mock(JavaMailSender.class);
        emailServices = new EmailServices(javaMailSender);
    }

    @Test
    void sendEmail_DeveChamarJavaMailSenderSend() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("teste@teste.com");
        message.setSubject("Assunto");
        message.setText("Corpo do e-mail");

        emailServices.sendEmail(message);

        verify(javaMailSender, times(1)).send(message);
    }
}