package com.example.studentmanagement.service;

import com.example.studentmanagement.models.StudentCource;
import com.example.studentmanagement.repository.StudentCourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCourcesService {
    @Autowired
    StudentCourcesRepository repository;

    public List<StudentCource> getStudentCourceList(){
        return repository.findAll();
    }

}
