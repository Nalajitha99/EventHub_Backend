package com.example.EventHub.Services.ServiceImplementations;

import com.example.EventHub.Models.Domains.Event;
import com.example.EventHub.Models.Dtos.EventDto;
import com.example.EventHub.Repositories.EventRepository;
import com.example.EventHub.Services.ServiceInterfaces.IEventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements IEventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ModelMapper modelMapper;

    public EventDto saveEvent(EventDto eventDto){
        Event event = modelMapper.map(eventDto, Event.class);
        Event e = eventRepository.save(event);
        return modelMapper.map(e, EventDto.class);
    }

    public EventDto getEventById(String eventId){
        Event event = eventRepository.getEventByEventID(eventId);
        return modelMapper.map(event, EventDto.class);
    }
}
