package com.example.studentmanagement.controller;

import com.example.studentmanagement.models.Admin;
import com.example.studentmanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService service;

    @GetMapping()
    public ResponseEntity<List<Admin>> getAdminList(){
        return ResponseEntity.ok(service.getAdminList());
    }

}
