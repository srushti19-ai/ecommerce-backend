//package com.ecommerce.ecommerce_backend.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//
//@Entity
//public class Payment {
//
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String razorpayOrderId;
//    private String razorpayPaymentId;
//    private String razorpaySignature;
//
//    private String status; 
//
//    public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getRazorpayOrderId() {
//		return razorpayOrderId;
//	}
//
//	public void setRazorpayOrderId(String razorpayOrderId) {
//		this.razorpayOrderId = razorpayOrderId;
//	}
//
//	public String getRazorpayPaymentId() {
//		return razorpayPaymentId;
//	}
//
//	public void setRazorpayPaymentId(String razorpayPaymentId) {
//		this.razorpayPaymentId = razorpayPaymentId;
//	}
//
//	public String getRazorpaySignature() {
//		return razorpaySignature;
//	}
//
//	public void setRazorpaySignature(String razorpaySignature) {
//		this.razorpaySignature = razorpaySignature;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public Order getOrder() {
//		return order;
//	}
//
//	public void setOrder(Order order) {
//		this.order = order;
//	}
//
//	@ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;
//    
//    
//}
