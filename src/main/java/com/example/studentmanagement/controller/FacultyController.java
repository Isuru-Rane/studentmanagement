package com.example.studentmanagement.controller;

import com.example.studentmanagement.models.Cources;
import com.example.studentmanagement.models.Faculties;
import com.example.studentmanagement.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultyController {
    @Autowired
    private FacultyService service;

   @GetMapping()
    public ResponseEntity<List<Faculties>> getFacultyList(){
        return ResponseEntity.ok(service.getFacultyList());
    }

    @PostMapping
    public ResponseEntity<Faculties> save(@RequestBody Faculties faculties){
        return ResponseEntity.ok(service.save(faculties));
    }
}
