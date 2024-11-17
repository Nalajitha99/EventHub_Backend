package com.example.EventHub.Services.ServiceImplementations;

import com.example.EventHub.Models.Domains.Event;
import com.example.EventHub.Models.Dtos.EventDto;
import com.example.EventHub.Repositories.EventRepository;
import com.example.EventHub.Services.ServiceInterfaces.IEventService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public void deleteEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException("Event not found with ID: " + eventId);
        }
        eventRepository.deleteById(eventId);
    }

    @Override
    public void updateTickets(String eventId, long purchasedTickets) {
        // Fetch the event by ID
        Optional<Event> eventOpt = eventRepository.findById(Long.valueOf(eventId));
        if (eventOpt.isPresent()) {
            Event event = eventOpt.get();

            // Update the available ticket count
            long newTicketCount = event.getNoOfTickets() - purchasedTickets;
            if (newTicketCount < 0) {
                throw new IllegalArgumentException("Not enough tickets available.");
            }

            event.setNoOfTickets(newTicketCount);
            eventRepository.save(event);
        } else {
            throw new IllegalArgumentException("Event not found.");
        }
    }

}
