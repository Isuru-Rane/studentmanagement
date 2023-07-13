package com.example.studentmanagement.service;

import com.example.studentmanagement.models.Admin;
import com.example.studentmanagement.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repository;

    public List<Admin> getAdminList(){
        return repository.findAll();
    }
}
