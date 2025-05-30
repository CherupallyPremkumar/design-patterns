package com.designpatterns.behavioral.chain;

/**
 * Technical support handler that handles more complex technical issues.
 */
public class TechnicalSupportHandler extends SupportHandler {

    public TechnicalSupportHandler() {
        super("Technical Support");
    }

    @Override
    public TicketResponse handle(SupportTicket ticket) {
        // Handle medium to high complexity technical issues
        if (ticket.getType() == Type.TECHNICAL && ticket.getComplexity() <= 7) {
            return new TicketResponse(true,
                    "Resolved technical issue: " + ticket.getDescription(),
                    getHandlerName());
        }

        // Handle bug reports of medium complexity
        if (ticket.getType() == Type.BUG && ticket.getComplexity() <= 5) {
            return new TicketResponse(true,
                    "Investigated and resolved bug: " + ticket.getDescription(),
                    getHandlerName());
        }

        // Pass to next handler if can't handle
        return handleNext(ticket);
    }
}