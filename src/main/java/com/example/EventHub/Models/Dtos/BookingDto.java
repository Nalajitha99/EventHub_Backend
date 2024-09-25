package com.example.EventHub.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingDto {
    private long bookingId;
    private long customerId;
    private long eventId;
    private int totalTickets;
    private LocalTime bookingTime;
    private long paymentId;
}
