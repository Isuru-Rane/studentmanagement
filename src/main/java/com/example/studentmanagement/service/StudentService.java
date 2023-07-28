package com.example.studentmanagement.service;

import com.example.studentmanagement.models.Student;
import com.example.studentmanagement.models.User;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository repository;

    public List<Student> getStudentList(){
        return repository.findAll();
    }
    public Student create(Student student){
        return repository.save(student);
    }

    public void delete(Integer id){
        Optional<Student> userOptional = repository.findById(id);
        if (userOptional.isEmpty()){
            throw new RuntimeException("user not found");
        }
        repository.delete(userOptional.get());
    }
}
