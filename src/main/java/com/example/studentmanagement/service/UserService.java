package com.example.studentmanagement.service;

import com.example.studentmanagement.models.User;
import com.example.studentmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getUserList(){
        return repository.findAll();
    }




}
