package com.example.studentmanagement;

import com.example.studentmanagement.service.Mail.thymeLeaf.EmailSenderService;
import com.example.studentmanagement.service.Mail.thymeLeaf.Mail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class ThymeleafSptingBootExampleApplicationTests {
    @Autowired
    EmailSenderService senderService;
    @Value("${spring.mail.username}") private String sender;
    @Test
    void contextLoads() throws IOException, MessagingException {


        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("name", "John Michel!");
        properties.put("location", "Sri Lanka");
        properties.put("sign", "Java Developer");

        Mail mail = Mail.builder()
                .from(sender)
                .to("isurudara1122@gmail.com")
                .htmlTemplate(new Mail.HtmlTemplate("sample", properties))
                .subject("This is sample email with spring boot and thymeleaf")
                .build();

        senderService.sendEmail(mail);
    }
}
