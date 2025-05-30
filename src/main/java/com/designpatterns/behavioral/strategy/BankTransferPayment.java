package com.designpatterns.behavioral.strategy;

/**
 * BankTransferPayment strategy implementing bank transfer payment processing.
 */
public class BankTransferPayment implements PaymentStrategy {
    private String bankName;
    private String accountNumber;
    private String routingNumber;

    public BankTransferPayment(String bankName, String accountNumber, String routingNumber) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
    }

    @Override
    public boolean pay(double amount) {
        if (!validatePayment()) {
            return false;
        }
        System.out.println("Paying " + amount + " using Bank Transfer from " + bankName);
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "BANK_TRANSFER";
    }

    @Override
    public boolean validatePayment() {
        return bankName != null &&
                !bankName.isEmpty() &&
                accountNumber != null &&
                accountNumber.length() >= 8 &&
                routingNumber != null &&
                routingNumber.length() == 9;
    }

    // Getters for testing
    public String getBankName() {
        return bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }
}