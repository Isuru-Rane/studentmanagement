package com.example.studentmanagement.repository;

import com.example.studentmanagement.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam,Integer> {
}
