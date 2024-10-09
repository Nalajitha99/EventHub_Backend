package com.example.EventHub.Controllers;

import com.example.EventHub.Models.Domains.Event;
import com.example.EventHub.Models.Dtos.EventDto;
import com.example.EventHub.Services.ServiceInterfaces.IEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
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
    public ResponseEntity<EventDto> saveEvent(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("venue") String venue,
            @RequestParam("venueType") String venueType,
            @RequestParam("location") String location,
            @RequestParam("noOfTickets") long noOfTickets,
            @RequestParam("ticketPrice") double ticketPrice,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam("startTime") LocalTime startTime,
            @RequestParam("endTime") LocalTime endTime,
            @RequestParam("imageData") MultipartFile imageData) throws IOException {

        EventDto eventDto = new EventDto();
        eventDto.setTitle(title);
        eventDto.setDescription(description);
        eventDto.setCategory(category);
        eventDto.setVenue(venue);
        eventDto.setVenueType(venueType);
        eventDto.setLocation(location);
        eventDto.setNoOfTickets(noOfTickets);
        eventDto.setTicketPrice(ticketPrice);
        eventDto.setDate(date);
        eventDto.setStartTime(startTime);
        eventDto.setEndTime(endTime);
        eventDto.setImageData(imageData.getBytes()); // Save the image data as byte array

        return ResponseEntity.ok(eventService.saveEvent(eventDto));
    }

    @GetMapping("getEventByID/{eventId}")
    public ResponseEntity<EventDto> getEventByID(@PathVariable String eventId) {
        try {
            EventDto eventDto = eventService.getEventByID(eventId);
            if (eventDto != null) {
                return ResponseEntity.ok(eventDto);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/venue/{venue}")
    public List<Event> getEventsByVenue(@PathVariable String venue) {
        return eventService.getEventsByVenue(venue);
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

    @PutMapping("/updateTickets/{eventId}")
    public ResponseEntity<String> updateTickets(
            @PathVariable String eventId,
            @RequestParam("purchasedTickets") long purchasedTickets
    ) {
        try {
            eventService.updateTickets(eventId, purchasedTickets);
            return ResponseEntity.ok("Tickets updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Error updating tickets: " + e.getMessage());
        }
    }



}
