package com.example.studentmanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(nullable = false, length = 45)
    private String status;

    @ManyToOne
    @JsonIgnore
    Exam exam;

    @ManyToOne
    @JsonIgnore
    StudentCource studentCource;


}
