package com.example.studentmanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(nullable = false, length = 45)
    private String name;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Cources> courcesList = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Student> studentList = new java.util.ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" ,referencedColumnName = "id")
    private User user;


}
