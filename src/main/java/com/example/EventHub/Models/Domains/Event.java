package com.example.EventHub.Models.Domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Event {
    @Id
    private long eventId;
    private String title;
    private String description;
    private String category;
    private String venue;
    private String venueType;
    private String Location;
    private long noOfTickets;
    private double ticketPrice;
    private Date date;
    private LocalTime startTime;
}
