package com.example.EventHub.Services.ServiceInterfaces;

import com.example.EventHub.Models.Domains.Event;
import com.example.EventHub.Models.Dtos.EventDto;

import java.util.List;

public interface IEventService {
    EventDto saveEvent(EventDto eventDto);
    EventDto getEventByID(String eventID);
    List<EventDto> getAllEvents();
    void deleteEventById(long eventId);
    List<Event> getEventsByVenue(String venue);

    void updateTickets(String eventId, long purchasedTickets);

}
