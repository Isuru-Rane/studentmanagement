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

    public void sendSimpleMail(MailDto details) {
        log.info("send Email. details = {}",details.toString());


        try {

            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getReceiver());
            mailMessage.setText(details.getBody());
            mailMessage.setSubject(details.getSubject());

            mailSender.send(mailMessage);
            log.info("mail successfully send");
        }

        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());

        }

    }



}
