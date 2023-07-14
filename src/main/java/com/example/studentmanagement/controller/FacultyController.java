package com.example.studentmanagement.controller;


import com.example.studentmanagement.models.Faculties;
import com.example.studentmanagement.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
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

    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Integer id){service.deleteFaculty(id);}
}
