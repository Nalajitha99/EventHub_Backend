package com.example.EventHub.Controllers;

import com.example.EventHub.Models.Dtos.EventDto;
import com.example.EventHub.Services.ServiceInterfaces.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> saveEvent(@RequestBody EventDto eventDto){
        try{
            return ResponseEntity.ok(eventService.saveEvent(eventDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("getEventByID/{eventId}")
    public EventDto getUserByID(@PathVariable String userId){
        return eventService.getEventByID(userId);
    }
}
