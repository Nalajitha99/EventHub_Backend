package com.example.EventHub.Services.ServiceInterfaces;

import com.example.EventHub.Models.Dtos.PaymentDto;

import java.util.List;

public interface IPaymentService {
    PaymentDto savePayment(PaymentDto paymentDto);
    List<PaymentDto> getAllPayments();
}
