package com.example.EventHub.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDto {
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
