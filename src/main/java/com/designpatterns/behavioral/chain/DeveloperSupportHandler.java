package com.designpatterns.behavioral.chain;

/**
 * Developer support handler that handles complex technical issues and bugs.
 */
public class DeveloperSupportHandler extends SupportHandler {

    public DeveloperSupportHandler() {
        super("Developer Support");
    }

    @Override
    public TicketResponse handle(SupportTicket ticket) {
        // Handle high complexity technical issues
        if (ticket.getType() == Type.TECHNICAL && ticket.getComplexity() > 7) {
            return new TicketResponse(true,
                    "Developer resolved complex technical issue: " + ticket.getDescription(),
                    getHandlerName());
        }

        // Handle complex bugs
        if (ticket.getType() == Type.BUG &&
                (ticket.getComplexity() > 5 || ticket.getPriority() == Priority.CRITICAL)) {
            return new TicketResponse(true,
                    "Developer investigated and fixed critical bug: " + ticket.getDescription(),
                    getHandlerName());
        }

        // Handle feature requests
        if (ticket.getType() == Type.FEATURE_REQUEST) {
            return new TicketResponse(true,
                    "Developer evaluated feature request: " + ticket.getDescription(),
                    getHandlerName());
        }

        // Pass to next handler if can't handle
        return handleNext(ticket);
    }
}