package com.example.studentmanagement.service;

import com.example.studentmanagement.models.User;
import com.example.studentmanagement.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getUserList(){
        return repository.findAll();
    }

    public User create(User user){
        return repository.save(user);
    }



}
