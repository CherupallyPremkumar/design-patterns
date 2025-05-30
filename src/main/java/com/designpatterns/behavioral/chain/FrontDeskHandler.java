package com.designpatterns.behavioral.chain;

/**
 * Front desk handler that handles basic support requests.
 */
public class FrontDeskHandler extends SupportHandler {

    public FrontDeskHandler() {
        super("Front Desk Support");
    }

    @Override
    public TicketResponse handle(SupportTicket ticket) {
        // Handle general inquiries and low complexity issues
        if (ticket.getType() == Type.GENERAL && ticket.getComplexity() <= 3) {
            return new TicketResponse(true,
                    "Resolved general inquiry: " + ticket.getDescription(),
                    getHandlerName());
        }

        // Handle basic technical issues
        if (ticket.getType() == Type.TECHNICAL && ticket.getComplexity() <= 2
                && ticket.getPriority() != Priority.CRITICAL) {
            return new TicketResponse(true,
                    "Resolved basic technical issue: " + ticket.getDescription(),
                    getHandlerName());
        }

        // Pass to next handler if can't handle
        return handleNext(ticket);
    }
}