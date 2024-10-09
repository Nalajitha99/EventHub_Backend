package com.example.EventHub.Services.ServiceImplementations;

import com.example.EventHub.Models.Domains.EventOrganizer;
import com.example.EventHub.Models.Domains.User;
import com.example.EventHub.Models.Dtos.EventOrganizerDto;
import com.example.EventHub.Models.Dtos.UserDto;
import com.example.EventHub.Repositories.EventOrganizerRepository;
import com.example.EventHub.Services.ServiceInterfaces.IEventOrganizerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventOrganizerServiceImpl implements IEventOrganizerService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventOrganizerRepository eventOrganizerRepository;

    public EventOrganizerDto saveEventOrganizer(EventOrganizerDto eventOrganizerDto) {
        EventOrganizer eventOrganizer = modelMapper.map(eventOrganizerDto, EventOrganizer.class);
        eventOrganizerRepository.save(eventOrganizer);
        return eventOrganizerDto;
    }

    public List<EventOrganizerDto> getAllEventOrganizers(){
        List<EventOrganizer> eventOrganizers = eventOrganizerRepository.findAll();
        return eventOrganizers.stream()
                .map(eventOrganizer -> modelMapper.map(eventOrganizer, EventOrganizerDto.class))
                .collect(Collectors.toList());
    }

    public EventOrganizerDto getEventOrganizerById(String organizerId) {
        EventOrganizer eventOrganizer = this.eventOrganizerRepository.getEventOrganizerById(organizerId);
        return (EventOrganizerDto)this.modelMapper.map(eventOrganizer, EventOrganizerDto.class);
    }
}
