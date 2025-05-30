package com.designpatterns.behavioral.chain;

/**
 * Abstract base handler class in the Chain of Responsibility pattern.
 * Defines the interface for handling requests and maintains the successor link.
 */
public abstract class SupportHandler {
    protected SupportHandler nextHandler;
    protected String handlerName;

    public SupportHandler(String handlerName) {
        this.handlerName = handlerName;
    }

    public SupportHandler setNext(SupportHandler handler) {
        this.nextHandler = handler;
        return handler;
    }

    public abstract TicketResponse handle(SupportTicket ticket);

    protected TicketResponse handleNext(SupportTicket ticket) {
        if (nextHandler != null) {
            return nextHandler.handle(ticket);
        }
        // If no handler can process the ticket, escalate to manager
        return new TicketResponse(false,
                "Ticket requires manager attention: " + ticket.getDescription(),
                "Management Escalation");
    }

    protected String getHandlerName() {
        return handlerName;
    }
}