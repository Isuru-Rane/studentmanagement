package com.example.studentmanagement.service;

import com.example.studentmanagement.models.Faculties;
import com.example.studentmanagement.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository repository;

    public List<Faculties> getFacultyList(){
        return repository.findAll();
    }
}
