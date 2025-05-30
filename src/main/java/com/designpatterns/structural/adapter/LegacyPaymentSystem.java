package com.designpatterns.structural.adapter;

/**
 * LegacyPaymentSystem representing an old payment system with a different
 * interface.
 * This is the Adaptee in the Adapter pattern.
 */
public class LegacyPaymentSystem {
    private String lastTransactionId;

    public String makePayment(PaymentData data) {
        // Simulate legacy payment processing
        if (data.amount <= 0) {
            return null;
        }

        lastTransactionId = generateTransactionId(data);
        System.out.println("Legacy system processing payment: " + data.toString());
        return lastTransactionId;
    }

    public int getTransactionStatus(String transactionId) {
        // Legacy status codes: 0 = pending, 1 = completed, 2 = failed, 3 = refunded
        if (transactionId == null || transactionId.isEmpty()) {
            return 2; // Failed
        }
        return 1; // Completed for demo
    }

    private String generateTransactionId(PaymentData data) {
        return "LEGACY_" + System.currentTimeMillis();
    }

    public String getLastTransactionId() {
        return lastTransactionId;
    }
}

/**
 * Data class used by the legacy system
 */
class PaymentData {
    double amount;
    String currencyCode;
    String customerDetails;

    public PaymentData(double amount, String currencyCode, String customerDetails) {
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.customerDetails = customerDetails;
    }

    @Override
    public String toString() {
        return "PaymentData{" +
                "amount=" + amount +
                ", currencyCode='" + currencyCode + '\'' +
                ", customerDetails='" + customerDetails + '\'' +
                '}';
    }
}