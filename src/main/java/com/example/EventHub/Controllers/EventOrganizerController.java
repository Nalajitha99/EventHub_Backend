package com.example.EventHub.Controllers;

import com.example.EventHub.Models.Dtos.EventOrganizerDto;
import com.example.EventHub.Models.Dtos.UserDto;
import com.example.EventHub.Services.ServiceInterfaces.IEventOrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "api/v1/eventOrganizer")
public class EventOrganizerController {

    @Autowired
    private IEventOrganizerService eventOrganizerService;

    @Autowired
    public EventOrganizerController(IEventOrganizerService eventOrganizerService) {
        this.eventOrganizerService = eventOrganizerService;
    }

    @PostMapping("/saveEventOrganizer")
    public ResponseEntity<?> saveEventOrganizer(@RequestBody EventOrganizerDto eventOrganizerDto){
        try{
            return ResponseEntity.ok(eventOrganizerService.saveEventOrganizer(eventOrganizerDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}
