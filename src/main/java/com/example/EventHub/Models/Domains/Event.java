package com.example.EventHub.Models.Domains;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Lob
    private byte[] imageData;
}
