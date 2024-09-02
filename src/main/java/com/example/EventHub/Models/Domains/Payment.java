package com.example.EventHub.Models.Domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Payment {
    @Id
    private long paymentId;
    private long eventId;
    private int totalTicket;
    private double totalPrice;
}
