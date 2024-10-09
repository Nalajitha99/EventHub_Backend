package com.example.EventHub.Models.Dtos;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDto {
    private long eventId;
    private String title;
    private String description;
    private String category;
    private String venue;
    private String venueType;
    private String location;
    private long noOfTickets;
    private double ticketPrice;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;
    private byte[] imageData;

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getNoOfTickets() {
        return noOfTickets;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNoOfTickets(long noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
