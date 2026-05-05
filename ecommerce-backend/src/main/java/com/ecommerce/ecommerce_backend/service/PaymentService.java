package com.ecommerce.ecommerce_backend.service;

//@Service
//public class PaymentService {
//	@Autowired
//    private OrderRepository orderRepository;
//
//    public String makePayment(Long orderId) {
//
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//
//        // mock payment
//        order.setPaymentStatus("PAID");
//        order.setPaymentId("PAY" + System.currentTimeMillis());
//
//        order.setStatus("COMPLETED"); 
//
//        orderRepository.save(order);
//
//        return "Payment Successful";
//    }
//}


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce_backend.repository.OrderRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentService {

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;
    
    @Autowired
    private OrderRepository orderRepository;

//    @Autowired
//    private PaymentRepository paymentRepository;

    public String createOrder(int amount) {

        try {
            RazorpayClient client = new RazorpayClient(keyId, keySecret);

            JSONObject options = new JSONObject();
            options.put("amount", amount * 100); // amt
            options.put("currency", "INR");
            options.put("receipt", "txn_123");

            Order order = client.orders.create(options);

            return order.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating order";
        }
    }
    
    private static final String SECRET = "YOUR_RAZORPAY_SECRET";

    public String verifyPayment(String orderId, String paymentId, String signature) {

        try {
            String data = orderId + "|" + paymentId;

            String generatedSignature = hmacSHA256(data, SECRET);

            if (generatedSignature.equals(signature)) {
                return "Payment VERIFIED SUCCESSFULLY";
            } else {
                return "Payment VERIFICATION FAILED";
            }

        } catch (Exception e) {
            return "Error in verification";
        }
    }
    

    private String hmacSHA256(String data, String secret) throws Exception {

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey =
                new SecretKeySpec(secret.getBytes(), "HmacSHA256");

        mac.init(secretKey);

        byte[] hash = mac.doFinal(data.getBytes());

        return bytesToHex(hash);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
    
}






