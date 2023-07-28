package com.example.studentmanagement.controller;

import com.example.studentmanagement.models.Student;
import com.example.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService service;


    @GetMapping
    public ResponseEntity<List<Student>> getStudentList(){
        return ResponseEntity.ok(service.getStudentList());
    }

    /*@PostMapping()
    public ResponseEntity<List<Student>> createStudent(@Valid MultipartHttpServletRequest request){

    }*/


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        service.delete(id);
    }

}
