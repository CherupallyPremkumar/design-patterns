package com.designpatterns.behavioral.mediator;

/**
 * Mediator interface defining the contract for chat room operations
 */
public interface ChatMediator {
    void sendMessage(String message, User user);

    void addUser(User user);

    void removeUser(User user);

    boolean isUserOnline(String username);

    void sendPrivateMessage(String message, User sender, String recipientUsername);

    void broadcastMessage(String message, User sender);
}