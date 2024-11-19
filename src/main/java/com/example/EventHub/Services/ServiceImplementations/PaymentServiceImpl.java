package com.example.EventHub.Services.ServiceImplementations;

import com.example.EventHub.Models.Domains.Payment;
import com.example.EventHub.Models.Domains.User;
import com.example.EventHub.Models.Dtos.PaymentDto;
import com.example.EventHub.Models.Dtos.UserDto;
import com.example.EventHub.Repositories.PaymentRepository;
import com.example.EventHub.Services.ServiceInterfaces.IPaymentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<PaymentDto> getAllPayments(){
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(payment -> modelMapper.map(payment, PaymentDto.class))
                .collect(Collectors.toList());
    }
}
