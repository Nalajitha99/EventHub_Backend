package com.example.EventHub.Services.ServiceInterfaces;

import com.example.EventHub.Models.Dtos.PaymentDto;

import java.util.List;
import java.util.Map;

public interface IPaymentService {
    PaymentDto savePayment(PaymentDto paymentDto);
    List<PaymentDto> getAllPayments();
    List<Map<String, Object>> getTotalTicketsSoldByEvent();
}
