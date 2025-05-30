package com.designpatterns.structural.adapter;

/**
 * LegacyPaymentAdapter adapts the LegacyPaymentSystem to work with the new
 * PaymentProcessor interface.
 * This is the Adapter in the Adapter pattern.
 */
public class LegacyPaymentAdapter implements PaymentProcessor {
    private final LegacyPaymentSystem legacySystem;

    public LegacyPaymentAdapter(LegacyPaymentSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public boolean processPayment(double amount, String currency, String customerName) {
        PaymentData data = new PaymentData(amount, currency, customerName);
        String transactionId = legacySystem.makePayment(data);
        return transactionId != null;
    }

    @Override
    public PaymentStatus checkStatus(String transactionId) {
        int legacyStatus = legacySystem.getTransactionStatus(transactionId);
        switch (legacyStatus) {
            case 0:
                return PaymentStatus.PENDING;
            case 1:
                return PaymentStatus.COMPLETED;
            case 2:
                return PaymentStatus.FAILED;
            case 3:
                return PaymentStatus.REFUNDED;
            default:
                return PaymentStatus.FAILED;
        }
    }

    @Override
    public String getLastTransaction() {
        return legacySystem.getLastTransactionId();
    }
}