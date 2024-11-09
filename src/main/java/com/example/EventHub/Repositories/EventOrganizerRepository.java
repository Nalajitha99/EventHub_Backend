package com.example.EventHub.Repositories;

import com.example.EventHub.Models.Domains.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventOrganizerRepository extends JpaRepository<EventOrganizer, Long> {

    @Query(value = "SELECT * FROM EVENT_ORGANIZER WHERE ORGANIZER_ID = ?1", nativeQuery = true)
    EventOrganizer getEventOrganizerById(String OrganizerId);
}
