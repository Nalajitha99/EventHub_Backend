package com.example.EventHub.Models.Domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;
    private long eventId;
    private int ticketCount;
    private double totalPrice;
    private long cardNumber;
    private String expiryDate;
    private String cvv;
    private String username;

    public long getPaymentId() {
        return paymentId;
    }

    public long getEventId() {
        return eventId;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public String getUsername() {
        return username;
    }
}
