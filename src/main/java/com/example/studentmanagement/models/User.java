package com.example.studentmanagement.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, name = "psw")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


}
