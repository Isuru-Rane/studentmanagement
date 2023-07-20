package com.example.studentmanagement.controller;


import com.example.studentmanagement.service.CourcesService;
import com.example.studentmanagement.models.Cources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cources")
public class CourcesController {

    @Autowired
    CourcesService service;

    @GetMapping
    public ResponseEntity <List<Cources>>getCourcesList(){
        return ResponseEntity.ok(service.getList());
    }

    @PostMapping
    public ResponseEntity<Cources> save(@RequestBody Cources cources){
        return ResponseEntity.ok(service.save(cources));
    }
}
