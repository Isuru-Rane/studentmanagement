package com.example.studentmanagement.controller;

import com.example.studentmanagement.models.User;
import com.example.studentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user)  {
        return ResponseEntity.ok(service.create(user));
    }




}