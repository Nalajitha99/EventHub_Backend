package com.example.EventHub.Repositories;

import com.example.EventHub.Models.Domains.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT * FROM EVENT WHERE EVENT_ID = ?1", nativeQuery = true)
    Event getEventByEventID(String eventID);

}
