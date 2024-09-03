package com.example.EventHub.Services.ServiceImplementations;

import com.example.EventHub.Models.Domains.Payment;
import com.example.EventHub.Models.Dtos.PaymentDto;
import com.example.EventHub.Repositories.PaymentRepository;
import com.example.EventHub.Services.ServiceInterfaces.IPaymentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    public PaymentDto savePayment(PaymentDto paymentDto){
        paymentRepository.save(modelMapper.map(paymentDto, Payment.class));
        return paymentDto;
    }
}
