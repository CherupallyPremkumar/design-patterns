package com.designpatterns.structural.adapter;

/**
 * PaymentProcessor interface defining the standard payment processing contract.
 * This is the Target interface in the Adapter pattern.
 */
public interface PaymentProcessor {
    boolean processPayment(double amount, String currency, String customerName);

    PaymentStatus checkStatus(String transactionId);

    String getLastTransaction();
}

/**
 * Enum representing possible payment statuses
 */
enum PaymentStatus {
    PENDING,
    COMPLETED,
    FAILED,
    REFUNDED
}