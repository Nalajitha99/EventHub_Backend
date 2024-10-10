package com.example.EventHub.Controllers;

import com.example.EventHub.Models.Dtos.PaymentDto;
import com.example.EventHub.Services.ServiceInterfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "api/v1/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @Autowired

    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/savePayment")
    public ResponseEntity<?> savePayment(@RequestBody PaymentDto paymentDto){
        try{
            return ResponseEntity.ok(paymentService.savePayment(paymentDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
