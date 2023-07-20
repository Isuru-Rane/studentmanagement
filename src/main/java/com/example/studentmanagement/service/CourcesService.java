package com.example.studentmanagement.service;

import com.example.studentmanagement.repository.AdminRepository;
import com.example.studentmanagement.repository.CourcesRepository;
import com.example.studentmanagement.repository.FacultyRepository;
import com.example.studentmanagement.models.Cources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CourcesService {
    @Autowired
    CourcesRepository repository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    FacultyRepository facultiesRepository;

    public List<Cources> getList(){
        return repository.findAll();

    }
    @Transactional
    public Cources save(Cources cources){
        cources.setAdmin(adminRepository.findById(1).get());
        cources.setFaculties(facultiesRepository.findById(1).get());
        return repository.save(cources);
    }



}
