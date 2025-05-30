package com.designpatterns.behavioral.strategy;

/**
 * PaymentProcessor class acting as the context in the Strategy pattern.
 * This class maintains a reference to the current payment strategy and
 * delegates to it.
 */
public class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean processPayment(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }

        return paymentStrategy.pay(amount);
    }

    public String getCurrentPaymentMethod() {
        if (paymentStrategy == null) {
            return "NO_PAYMENT_METHOD_SET";
        }
        return paymentStrategy.getPaymentMethod();
    }
}