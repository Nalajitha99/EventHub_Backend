package com.example.EventHub.Repositories;

import com.example.EventHub.Models.Domains.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventOrganizerRepository extends JpaRepository<EventOrganizer, Long> {
}
