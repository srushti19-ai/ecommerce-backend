package com.ecommerce.ecommerce_backend.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce_backend.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
    private PaymentService paymentService;
	
	@GetMapping("/test")
	public String test() {
	    return "Payment API working";
	}

    @PostMapping("/pay")
    public String pay(@RequestParam Long orderId) {
        return paymentService.makePayment(orderId);
    }
}
