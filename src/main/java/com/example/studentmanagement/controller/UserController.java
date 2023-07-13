package com.example.studentmanagement.controller;

import com.example.studentmanagement.models.User;
import com.example.studentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping()
    public ResponseEntity<List<User>> getUserList(){
        return ResponseEntity.ok(service.getUserList());
    }






}