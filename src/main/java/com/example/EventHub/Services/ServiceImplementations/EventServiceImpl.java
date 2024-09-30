package com.example.EventHub.Services.ServiceImplementations;

import com.example.EventHub.Models.Domains.Event;
import com.example.EventHub.Models.Domains.User;
import com.example.EventHub.Models.Dtos.EventDto;
import com.example.EventHub.Models.Dtos.UserDto;
import com.example.EventHub.Repositories.EventRepository;
import com.example.EventHub.Services.ServiceInterfaces.IEventService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventServiceImpl implements IEventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @Override
//    public EventDto saveEvent(EventDto eventDto){
//        Event event = modelMapper.map(eventDto, Event.class);
//        Event e = eventRepository.save(event);
//        return modelMapper.map(e, EventDto.class);
//    }

    public EventDto saveEvent(EventDto eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);
        eventRepository.save(event);
        return eventDto;
    }

    public EventDto getEventByID(String eventId){
        Event event = this.eventRepository.getEventByEventID(eventId);
        return (EventDto)this.modelMapper.map(event, EventDto.class);
    }

    @Override
    public List<EventDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(event -> modelMapper.map(event, EventDto.class))
                .collect(Collectors.toList());
    }

    public List<Event> getEventsByVenue(String venue) {
        return eventRepository.findByVenue(venue);
    }

    @Override
    public void deleteEventById(long eventId) {
        if (eventRepository.existsById(eventId)) {
            eventRepository.deleteById(eventId);
        } else {
            throw new IllegalArgumentException("Event with ID " + eventId + " not found");
        }
    }
}
