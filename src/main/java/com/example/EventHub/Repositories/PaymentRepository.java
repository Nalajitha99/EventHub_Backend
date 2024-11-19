package com.example.EventHub.Repositories;

import com.example.EventHub.Models.Domains.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p.eventId AS eventId, SUM(p.ticketCount) AS ticketCount " +
            "FROM Payment p GROUP BY p.eventId")
    List<Map<String, Object>> findEventTicketCounts();
}
