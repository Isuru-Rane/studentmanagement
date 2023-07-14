package com.example.studentmanagement.service;

import com.example.studentmanagement.models.Cources;
import com.example.studentmanagement.models.Faculties;
import com.example.studentmanagement.repository.AdminRepository;
import com.example.studentmanagement.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository repository;

    @Autowired
    private AdminRepository adminRepository;

    public List<Faculties> getFacultyList(){
        return repository.findAll();
    }

    @Transactional
    public Faculties save(Faculties faculties){
        for (Cources cources : faculties.getCourcesList()) {
            cources.setFaculties(faculties);
            cources.setAdmin(adminRepository.findById(1).get());
        }
        return repository.save(faculties);
    }
}
