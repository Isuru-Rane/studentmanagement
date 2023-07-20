package com.example.studentmanagement.repository;

import com.example.studentmanagement.models.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payments, Integer> {
}
