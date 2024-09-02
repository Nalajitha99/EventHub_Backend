package com.example.EventHub.Repositories;

import com.example.EventHub.Models.Domains.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
