package com.example.EventHub.Repositories;

import com.example.EventHub.Models.Domains.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
