package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.UserDto;
import com.example.studentmanagement.models.User;
import com.example.studentmanagement.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<User> createUser(@Valid MultipartHttpServletRequest request){
        Gson gson = new Gson();
        UserDto userDto = gson.fromJson(request.getParameter("user"),UserDto.class);
        userDto.setProfilePicture( request.getFile("profile_picture"));
        return ResponseEntity.ok(service.create(userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserListById(@PathVariable int id){
        return ResponseEntity.ok(service.findById(id));
    }

}