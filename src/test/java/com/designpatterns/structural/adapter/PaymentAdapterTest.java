package com.designpatterns.structural.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating Adapter pattern usage and verification.
 * Shows common interview scenarios and verification points.
 */
public class PaymentAdapterTest {
    private PaymentProcessor modernPaymentProcessor;
    private LegacyPaymentSystem legacySystem;

    @BeforeEach
    void setUp() {
        legacySystem = new LegacyPaymentSystem();
        modernPaymentProcessor = new LegacyPaymentAdapter(legacySystem);
    }

    @Test
    void testSuccessfulPayment() {
        boolean result = modernPaymentProcessor.processPayment(100.0, "USD", "John Doe");
        assertTrue(result);
        assertNotNull(modernPaymentProcessor.getLastTransaction());
        assertTrue(modernPaymentProcessor.getLastTransaction().startsWith("LEGACY_"));
    }

    @Test
    void testFailedPayment() {
        boolean result = modernPaymentProcessor.processPayment(-50.0, "USD", "John Doe");
        assertFalse(result);
    }

    @Test
    void testPaymentStatus() {
        modernPaymentProcessor.processPayment(100.0, "USD", "John Doe");
        String transactionId = modernPaymentProcessor.getLastTransaction();
        PaymentStatus status = modernPaymentProcessor.checkStatus(transactionId);
        assertEquals(PaymentStatus.COMPLETED, status);
    }

    @Test
    void testInvalidTransactionStatus() {
        PaymentStatus status = modernPaymentProcessor.checkStatus("");
        assertEquals(PaymentStatus.FAILED, status);
    }

    @Test
    void testAdapterIntegration() {
        // Process payment through adapter
        boolean paymentResult = modernPaymentProcessor.processPayment(200.0, "EUR", "Jane Doe");
        assertTrue(paymentResult);

        // Verify the legacy system was actually used
        String transactionId = legacySystem.getLastTransactionId();
        assertNotNull(transactionId);
        assertEquals(transactionId, modernPaymentProcessor.getLastTransaction());

        // Verify status translation
        PaymentStatus status = modernPaymentProcessor.checkStatus(transactionId);
        assertEquals(PaymentStatus.COMPLETED, status);
    }
}