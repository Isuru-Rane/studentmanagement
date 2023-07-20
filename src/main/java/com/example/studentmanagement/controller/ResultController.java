package com.example.studentmanagement.controller;

import com.example.studentmanagement.models.Result;
import com.example.studentmanagement.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {
    @Autowired
    ResultService service;


    @GetMapping()
    public ResponseEntity<List<Result>> getResultList(){
        return ResponseEntity.ok(service.getResultsList());
    }

}
