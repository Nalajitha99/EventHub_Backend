package com.example.EventHub.Controllers;

import com.example.EventHub.Models.Dtos.EventDto;
import com.example.EventHub.Services.ServiceInterfaces.IEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "api/v1/event")
public class EventController {

    @Autowired
    private IEventService eventService;

    @Autowired
    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/saveEvent")
    public ResponseEntity<EventDto> saveEvent(@RequestParam("event") String eventJson,
                                                @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        EventDto eventDto = objectMapper.readValue(eventJson, EventDto.class);

        if (image != null) {
            eventDto.setImageData(image.getBytes()); // Convert image to byte array
        }

        return ResponseEntity.ok(eventService.saveEvent(eventDto));
    }

    @GetMapping("getEventByID/{eventId}")
    public EventDto getEventByID(@PathVariable String eventId){
        return eventService.getEventByID(eventId);
    }

    @GetMapping("/getAllEvents")
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<EventDto> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @DeleteMapping("/deleteEvent/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") long eventId) {
        try {
            eventService.deleteEventById(eventId);
            return ResponseEntity.ok("Event deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
