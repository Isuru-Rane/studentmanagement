package com.example.studentmanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(nullable = false, length = 45)
    private String name;

    @ManyToOne
    Admin admin;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<StudentCource> studentCourceList = new java.util.ArrayList<>();


}
