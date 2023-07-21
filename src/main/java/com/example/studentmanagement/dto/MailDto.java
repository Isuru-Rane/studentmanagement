package com.example.studentmanagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class MailDto {
    private String receiver;
    private String body;
    private String subject;
}
