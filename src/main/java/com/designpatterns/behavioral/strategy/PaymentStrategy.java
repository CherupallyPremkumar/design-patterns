package com.designpatterns.behavioral.strategy;

/**
 * PaymentStrategy interface defining the contract for all payment strategies.
 * This is the Strategy interface in the Strategy pattern.
 */
public interface PaymentStrategy {
    boolean pay(double amount);

    String getPaymentMethod();

    boolean validatePayment();
}