package com.example.studentmanagement.service;

import com.example.studentmanagement.models.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository repository;

    public List<Student> getStudentList(){
        return repository.findAll();
    }
    public Student create(Student student){
        return repository.save(student);
    }
}
