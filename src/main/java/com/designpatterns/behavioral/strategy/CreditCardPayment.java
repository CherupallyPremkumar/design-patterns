package com.designpatterns.behavioral.strategy;

/**
 * CreditCardPayment strategy implementing credit card payment processing.
 */
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String name;
    private String cvv;
    private String dateOfExpiry;

    public CreditCardPayment(String cardNumber, String name, String cvv, String dateOfExpiry) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.cvv = cvv;
        this.dateOfExpiry = dateOfExpiry;
    }

    @Override
    public boolean pay(double amount) {
        if (!validatePayment()) {
            return false;
        }
        System.out.println("Paying " + amount + " using Credit Card");
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "CREDIT_CARD";
    }

    @Override
    public boolean validatePayment() {
        // Basic validation
        return cardNumber != null &&
                cardNumber.length() == 16 &&
                cvv != null &&
                cvv.length() == 3 &&
                dateOfExpiry != null &&
                !dateOfExpiry.isEmpty() &&
                name != null &&
                !name.isEmpty();
    }

    // Getters for testing
    public String getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public String getCvv() {
        return cvv;
    }

    public String getDateOfExpiry() {
        return dateOfExpiry;
    }
}