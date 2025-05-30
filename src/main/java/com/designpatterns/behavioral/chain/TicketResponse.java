package com.designpatterns.behavioral.chain;

/**
 * Represents a response from a support handler.
 */
public class TicketResponse {
    private final boolean handled;
    private final String message;
    private final String handlerName;

    public TicketResponse(boolean handled, String message, String handlerName) {
        this.handled = handled;
        this.message = message;
        this.handlerName = handlerName;
    }

    public boolean isHandled() {
        return handled;
    }

    public String getMessage() {
        return message;
    }

    public String getHandlerName() {
        return handlerName;
    }

    @Override
    public String toString() {
        return String.format("Response[handled=%b, handler='%s', message='%s']",
                handled, handlerName, message);
    }
}