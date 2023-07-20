package com.example.studentmanagement.service;

import com.example.studentmanagement.models.Exam;
import com.example.studentmanagement.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamRepository repository;

    public List<Exam> getExamList(){
        return repository.findAll();
    }
}
