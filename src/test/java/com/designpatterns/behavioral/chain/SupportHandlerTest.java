package com.designpatterns.behavioral.chain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating Chain of Responsibility pattern usage and
 * verification.
 * Shows common interview scenarios and verification points.
 */
public class SupportHandlerTest {
    private SupportHandler frontDesk;
    private SupportHandler technicalSupport;
    private SupportHandler developerSupport;
    private SupportHandler billingSupport;

    @BeforeEach
    void setUp() {
        // Create handlers
        frontDesk = new FrontDeskHandler();
        technicalSupport = new TechnicalSupportHandler();
        developerSupport = new DeveloperSupportHandler();
        billingSupport = new BillingHandler();

        // Set up the chain
        frontDesk.setNext(technicalSupport)
                .setNext(developerSupport)
                .setNext(billingSupport);
    }

    @Test
    void testBasicGeneralInquiry() {
        SupportTicket ticket = new SupportTicket(
                "How do I reset my password?",
                Priority.LOW,
                Type.GENERAL,
                2);

        TicketResponse response = frontDesk.handle(ticket);
        assertTrue(response.isHandled());
        assertEquals("Front Desk Support", response.getHandlerName());
    }

    @Test
    void testTechnicalIssueHandling() {
        SupportTicket ticket = new SupportTicket(
                "Server connection error",
                Priority.HIGH,
                Type.TECHNICAL,
                6);

        TicketResponse response = frontDesk.handle(ticket);
        assertTrue(response.isHandled());
        assertEquals("Technical Support", response.getHandlerName());
    }

    @Test
    void testCriticalBugHandling() {
        SupportTicket ticket = new SupportTicket(
                "Production database down",
                Priority.CRITICAL,
                Type.BUG,
                8);

        TicketResponse response = frontDesk.handle(ticket);
        assertTrue(response.isHandled());
        assertEquals("Developer Support", response.getHandlerName());
    }

    @Test
    void testBillingIssueHandling() {
        SupportTicket ticket = new SupportTicket(
                "Double charged for subscription",
                Priority.MEDIUM,
                Type.BILLING,
                4);

        TicketResponse response = frontDesk.handle(ticket);
        assertTrue(response.isHandled());
        assertEquals("Billing Support", response.getHandlerName());
    }

    @Test
    void testCriticalBillingEscalation() {
        SupportTicket ticket = new SupportTicket(
                "Large enterprise billing discrepancy",
                Priority.CRITICAL,
                Type.BILLING,
                9);

        TicketResponse response = frontDesk.handle(ticket);
        assertFalse(response.isHandled());
        assertTrue(response.getMessage().contains("Escalating critical billing issue"));
    }

    @Test
    void testFeatureRequestHandling() {
        SupportTicket ticket = new SupportTicket(
                "Add dark mode support",
                Priority.LOW,
                Type.FEATURE_REQUEST,
                5);

        TicketResponse response = frontDesk.handle(ticket);
        assertTrue(response.isHandled());
        assertEquals("Developer Support", response.getHandlerName());
    }

    @Test
    void testComplexityValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SupportTicket("Test", Priority.LOW, Type.GENERAL, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new SupportTicket("Test", Priority.LOW, Type.GENERAL, 11);
        });
    }

    @Test
    void testChainFallthrough() {
        // Create a ticket that no handler can handle
        SupportTicket ticket = new SupportTicket(
                "Unknown issue type",
                Priority.HIGH,
                Type.GENERAL,
                10);

        TicketResponse response = frontDesk.handle(ticket);
        assertFalse(response.isHandled());
        assertTrue(response.getMessage().contains("requires manager attention"));
    }

    @Test
    void testHandlerBypass() {
        // This ticket should bypass front desk and technical support
        SupportTicket ticket = new SupportTicket(
                "Critical system architecture issue",
                Priority.CRITICAL,
                Type.TECHNICAL,
                9);

        TicketResponse response = frontDesk.handle(ticket);
        assertTrue(response.isHandled());
        assertEquals("Developer Support", response.getHandlerName());
    }
}