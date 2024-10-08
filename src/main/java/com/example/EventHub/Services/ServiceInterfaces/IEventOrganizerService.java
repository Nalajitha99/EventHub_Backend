package com.example.EventHub.Services.ServiceInterfaces;

import com.example.EventHub.Models.Dtos.EventOrganizerDto;

public interface IEventOrganizerService {
    EventOrganizerDto saveEventOrganizer(EventOrganizerDto eventOrganizerDto);
}
