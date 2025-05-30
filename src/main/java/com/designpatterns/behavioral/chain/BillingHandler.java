package com.designpatterns.behavioral.chain;

/**
 * Billing support handler that handles billing-related issues.
 */
public class BillingHandler extends SupportHandler {

    public BillingHandler() {
        super("Billing Support");
    }

    @Override
    public TicketResponse handle(SupportTicket ticket) {
        if (ticket.getType() != Type.BILLING) {
            return handleNext(ticket);
        }

        // Handle billing issues based on complexity
        if (ticket.getComplexity() <= 5) {
            return new TicketResponse(true,
                    "Resolved billing issue: " + ticket.getDescription(),
                    getHandlerName());
        }

        // Handle complex billing issues
        if (ticket.getComplexity() > 5 && ticket.getPriority() != Priority.CRITICAL) {
            return new TicketResponse(true,
                    "Resolved complex billing issue: " + ticket.getDescription(),
                    getHandlerName());
        }

        // Escalate critical billing issues
        if (ticket.getPriority() == Priority.CRITICAL) {
            return new TicketResponse(false,
                    "Escalating critical billing issue to financial department: " + ticket.getDescription(),
                    getHandlerName());
        }

        return handleNext(ticket);
    }
}