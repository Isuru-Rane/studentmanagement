package com.example.studentmanagement.repository;

import com.example.studentmanagement.models.Faculties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculties , Integer> {
}
