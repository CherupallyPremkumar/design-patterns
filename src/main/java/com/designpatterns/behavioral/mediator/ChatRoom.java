package com.designpatterns.behavioral.mediator;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete Mediator implementing the chat room functionality
 */
public class ChatRoom implements ChatMediator {
    private final Map<String, User> users;

    public ChatRoom() {
        this.users = new HashMap<>();
    }

    @Override
    public void sendMessage(String message, User sender) {
        // Send message to all online users except sender
        users.values().stream()
                .filter(user -> !user.equals(sender) && user.isOnline())
                .forEach(user -> user.receive(message, sender));
    }

    @Override
    public void addUser(User user) {
        users.put(user.getUsername(), user);
        broadcastMessage(user.getUsername() + " joined the chat", user);
    }

    @Override
    public void removeUser(User user) {
        users.remove(user.getUsername());
        broadcastMessage(user.getUsername() + " left the chat", user);
    }

    @Override
    public boolean isUserOnline(String username) {
        User user = users.get(username);
        return user != null && user.isOnline();
    }

    @Override
    public void sendPrivateMessage(String message, User sender, String recipientUsername) {
        User recipient = users.get(recipientUsername);
        if (recipient != null && recipient.isOnline()) {
            recipient.receivePrivate(message, sender);
        } else {
            System.out.printf("Cannot send private message to %s: User is offline or doesn't exist%n",
                    recipientUsername);
        }
    }

    @Override
    public void broadcastMessage(String message, User sender) {
        users.values().stream()
                .filter(user -> !user.equals(sender) && user.isOnline())
                .forEach(user -> user.receiveBroadcast(message, sender));
    }

    public int getOnlineUsersCount() {
        return (int) users.values().stream()
                .filter(User::isOnline)
                .count();
    }

    public int getTotalUsersCount() {
        return users.size();
    }
}