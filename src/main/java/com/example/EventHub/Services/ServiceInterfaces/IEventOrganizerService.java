package com.example.EventHub.Services.ServiceInterfaces;

import com.example.EventHub.Models.Dtos.EventOrganizerDto;

import java.util.List;

public interface IEventOrganizerService {
    EventOrganizerDto saveEventOrganizer(EventOrganizerDto eventOrganizerDto);
    List<EventOrganizerDto> getAllEventOrganizers();
    EventOrganizerDto getEventOrganizerById(String organizerId);
}
