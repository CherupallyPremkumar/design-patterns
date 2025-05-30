package com.designpatterns.behavioral.strategy;

/**
 * PayPalPayment strategy implementing PayPal payment processing.
 */
public class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean pay(double amount) {
        if (!validatePayment()) {
            return false;
        }
        System.out.println("Paying " + amount + " using PayPal account: " + email);
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "PAYPAL";
    }

    @Override
    public boolean validatePayment() {
        return email != null &&
                email.contains("@") &&
                password != null &&
                password.length() >= 8;
    }

    // Getters for testing
    public String getEmail() {
        return email;
    }
}