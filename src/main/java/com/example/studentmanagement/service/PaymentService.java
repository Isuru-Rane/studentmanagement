package com.example.studentmanagement.service;

import com.example.studentmanagement.models.Payments;
import com.example.studentmanagement.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository repository;

    public List<Payments> getPaymentsList(){
        return repository.findAll();
    }



}
