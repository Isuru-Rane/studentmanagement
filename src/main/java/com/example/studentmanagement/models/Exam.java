package com.example.studentmanagement.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(nullable = false, length = 45)
    private String name;

    @ManyToOne
    Cources cources;

    @OneToMany(mappedBy = "exam")
    @JsonIgnore
    private List<Result> resultList = new java.util.ArrayList<>();


}
