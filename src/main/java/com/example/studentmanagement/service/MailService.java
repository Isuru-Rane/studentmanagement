package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.MailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendSimpleMail(String email, JavaMailSender mailSender) {

        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(email);
            mailMessage.setText("You have created an account");
            mailMessage.setSubject("Account successfully created");

            mailSender.send(mailMessage);
            log.info("email sent to {}",email);
        }

        catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(e.getMessage());

        }

    }



}
