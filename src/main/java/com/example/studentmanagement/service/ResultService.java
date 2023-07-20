package com.example.studentmanagement.service;

import com.example.studentmanagement.models.Result;
import com.example.studentmanagement.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    ResultRepository repository;

    public List<Result> getResultsList(){
        return repository.findAll();
    }
}
