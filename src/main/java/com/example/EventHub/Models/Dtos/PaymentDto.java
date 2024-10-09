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
}
