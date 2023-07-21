package com.example.studentmanagement.repository;

import com.example.studentmanagement.models.StudentCource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourcesRepository extends JpaRepository<StudentCource, Integer> {
}
