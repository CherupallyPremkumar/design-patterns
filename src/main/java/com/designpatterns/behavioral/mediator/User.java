package com.designpatterns.behavioral.mediator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Colleague class representing a chat user
 */
public class User {
    private final String username;
    private final ChatMediator mediator;
    private boolean isOnline;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public User(String username, ChatMediator mediator) {
        this.username = username;
        this.mediator = mediator;
        this.isOnline = true;
    }

    public void send(String message) {
        System.out.printf("%s [%s] sending: %s%n",
                LocalDateTime.now().format(TIME_FORMATTER),
                username,
                message);
        mediator.sendMessage(message, this);
    }

    public void sendPrivate(String message, String recipientUsername) {
        System.out.printf("%s [%s] sending private message to %s%n",
                LocalDateTime.now().format(TIME_FORMATTER),
                username,
                recipientUsername);
        mediator.sendPrivateMessage(message, this, recipientUsername);
    }

    public void broadcast(String message) {
        System.out.printf("%s [%s] broadcasting: %s%n",
                LocalDateTime.now().format(TIME_FORMATTER),
                username,
                message);
        mediator.broadcastMessage(message, this);
    }

    public void receive(String message, User sender) {
        if (isOnline) {
            System.out.printf("%s [%s] received from %s: %s%n",
                    LocalDateTime.now().format(TIME_FORMATTER),
                    username,
                    sender.getUsername(),
                    message);
        }
    }

    public void receivePrivate(String message, User sender) {
        if (isOnline) {
            System.out.printf("%s [%s] received private message from %s: %s%n",
                    LocalDateTime.now().format(TIME_FORMATTER),
                    username,
                    sender.getUsername(),
                    message);
        }
    }

    public void receiveBroadcast(String message, User sender) {
        if (isOnline) {
            System.out.printf("%s [%s] received broadcast from %s: %s%n",
                    LocalDateTime.now().format(TIME_FORMATTER),
                    username,
                    sender.getUsername(),
                    message);
        }
    }

    public String getUsername() {
        return username;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void goOnline() {
        isOnline = true;
        System.out.printf("%s [%s] is now online%n",
                LocalDateTime.now().format(TIME_FORMATTER),
                username);
    }

    public void goOffline() {
        isOnline = false;
        System.out.printf("%s [%s] is now offline%n",
                LocalDateTime.now().format(TIME_FORMATTER),
                username);
    }
}