package com.example.EventHub.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventOrganizerDto {
    private String organizerName;
    private String companyName;
    private String eventType;
    private long ticketCount;
    private String email;
    private String contactNo;
    private String message;
}
