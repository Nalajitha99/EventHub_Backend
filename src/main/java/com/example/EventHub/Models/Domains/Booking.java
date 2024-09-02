package com.example.EventHub.Models.Domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Booking {
    @Id
    private long bookingId;
    private long customerId;
    private long eventId;
    private int totalTickets;
    private LocalTime bookingTime;
    private long paymentId;
}
