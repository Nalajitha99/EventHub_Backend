package com.example.EventHub.Services.ServiceInterfaces;

import com.example.EventHub.Models.Dtos.PaymentDto;

public interface IPaymentService {
    PaymentDto savePayment(PaymentDto paymentDto);
}
