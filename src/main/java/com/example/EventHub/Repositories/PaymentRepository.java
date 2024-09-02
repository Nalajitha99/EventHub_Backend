package com.example.EventHub.Repositories;

import com.example.EventHub.Models.Domains.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
