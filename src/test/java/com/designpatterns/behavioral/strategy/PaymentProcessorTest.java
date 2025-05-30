package com.designpatterns.behavioral.strategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating Strategy pattern usage and verification.
 * Shows common interview scenarios and verification points.
 */
public class PaymentProcessorTest {

    @Test
    void testCreditCardPayment() {
        PaymentStrategy creditCard = new CreditCardPayment(
                "1234567890123456", // 16 digits
                "John Doe",
                "123", // 3 digits CVV
                "12/25");

        PaymentProcessor processor = new PaymentProcessor(creditCard);
        assertTrue(processor.processPayment(100.0));
        assertEquals("CREDIT_CARD", processor.getCurrentPaymentMethod());
    }

    @Test
    void testPayPalPayment() {
        PaymentStrategy payPal = new PayPalPayment(
                "john.doe@example.com",
                "password123");

        PaymentProcessor processor = new PaymentProcessor(payPal);
        assertTrue(processor.processPayment(50.0));
        assertEquals("PAYPAL", processor.getCurrentPaymentMethod());
    }

    @Test
    void testBankTransferPayment() {
        PaymentStrategy bankTransfer = new BankTransferPayment(
                "Chase Bank",
                "12345678", // 8 digits
                "987654321" // 9 digits routing
        );

        PaymentProcessor processor = new PaymentProcessor(bankTransfer);
        assertTrue(processor.processPayment(1000.0));
        assertEquals("BANK_TRANSFER", processor.getCurrentPaymentMethod());
    }

    @Test
    void testInvalidCreditCard() {
        PaymentStrategy invalidCard = new CreditCardPayment(
                "123", // Invalid card number
                "John Doe",
                "123",
                "12/25");

        PaymentProcessor processor = new PaymentProcessor(invalidCard);
        assertFalse(processor.processPayment(100.0));
    }

    @Test
    void testInvalidPayPal() {
        PaymentStrategy invalidPayPal = new PayPalPayment(
                "invalid-email", // Invalid email
                "pass" // Invalid password (too short)
        );

        PaymentProcessor processor = new PaymentProcessor(invalidPayPal);
        assertFalse(processor.processPayment(50.0));
    }

    @Test
    void testInvalidBankTransfer() {
        PaymentStrategy invalidTransfer = new BankTransferPayment(
                "Chase Bank",
                "123", // Invalid account number (too short)
                "12345" // Invalid routing number
        );

        PaymentProcessor processor = new PaymentProcessor(invalidTransfer);
        assertFalse(processor.processPayment(1000.0));
    }

    @Test
    void testChangeStrategy() {
        PaymentProcessor processor = new PaymentProcessor(
                new CreditCardPayment("1234567890123456", "John Doe", "123", "12/25"));
        assertEquals("CREDIT_CARD", processor.getCurrentPaymentMethod());

        processor.setPaymentStrategy(
                new PayPalPayment("john.doe@example.com", "password123"));
        assertEquals("PAYPAL", processor.getCurrentPaymentMethod());
    }

    @Test
    void testNullStrategy() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            PaymentProcessor processor = new PaymentProcessor(null);
            processor.processPayment(100.0);
        });
        assertTrue(exception.getMessage().contains("Payment strategy not set"));
    }

    @Test
    void testInvalidAmount() {
        PaymentProcessor processor = new PaymentProcessor(
                new CreditCardPayment("1234567890123456", "John Doe", "123", "12/25"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            processor.processPayment(-100.0);
        });
        assertTrue(exception.getMessage().contains("Payment amount must be greater than zero"));
    }
}