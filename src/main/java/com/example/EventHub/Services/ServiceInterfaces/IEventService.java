package com.example.EventHub.Services.ServiceInterfaces;

import com.example.EventHub.Models.Dtos.EventDto;

public interface IEventService {
    EventDto saveEvent(EventDto eventDto);
    EventDto getEventByID(String eventID);
}
