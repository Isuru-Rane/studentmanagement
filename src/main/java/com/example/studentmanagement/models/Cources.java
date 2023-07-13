package com.example.studentmanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cources")
public class Cources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(nullable = false, length = 45)
    private String name;

    private int fee;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "faculties_id", referencedColumnName = "id")
    @JsonIgnore
    Faculties faculties;

    @ManyToOne
    @JsonIgnore
    Admin admin;

    @OneToMany(mappedBy = "cources")
    @JsonIgnore
    private List<Exam> examList = new java.util.ArrayList<>();

}
