package com.designpatterns.behavioral.mediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatRoomTest {
    private ChatRoom chatRoom;
    private User alice;
    private User bob;
    private User charlie;

    @BeforeEach
    void setUp() {
        chatRoom = new ChatRoom();
        alice = new User("Alice", chatRoom);
        bob = new User("Bob", chatRoom);
        charlie = new User("Charlie", chatRoom);
    }

    @Test
    void testUserJoining() {
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        assertEquals(2, chatRoom.getTotalUsersCount());
        assertEquals(2, chatRoom.getOnlineUsersCount());
    }

    @Test
    void testUserGoingOfflineOnline() {
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);

        bob.goOffline();
        assertTrue(alice.isOnline());
        assertFalse(bob.isOnline());
        assertEquals(1, chatRoom.getOnlineUsersCount());

        bob.goOnline();
        assertTrue(bob.isOnline());
        assertEquals(2, chatRoom.getOnlineUsersCount());
    }

    @Test
    void testPrivateMessaging() {
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);

        alice.sendPrivate("Hey Bob, how are you?", "Bob");
        assertTrue(chatRoom.isUserOnline("Bob"));

        // Test sending to offline user
        bob.goOffline();
        alice.sendPrivate("Are you there?", "Bob");
        assertFalse(chatRoom.isUserOnline("Bob"));
    }

    @Test
    void testBroadcastMessage() {
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);

        alice.broadcast("Hello everyone!");
        assertEquals(3, chatRoom.getTotalUsersCount());

        // Test broadcast with some offline users
        bob.goOffline();
        charlie.goOffline();
        alice.broadcast("Is anyone still here?");
        assertEquals(1, chatRoom.getOnlineUsersCount());
    }

    @Test
    void testUserRemoval() {
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        assertEquals(2, chatRoom.getTotalUsersCount());

        chatRoom.removeUser(alice);
        assertEquals(1, chatRoom.getTotalUsersCount());
        assertFalse(chatRoom.isUserOnline("Alice"));
    }

    @Test
    void testMessageToNonexistentUser() {
        chatRoom.addUser(alice);
        alice.sendPrivate("Hello", "NonexistentUser");
        // Should not throw exception, just print error message
    }

    @Test
    void testChatRoomFunctionality() {
        // Add users
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);

        // Send messages
        alice.send("Hello everyone!");
        bob.sendPrivate("Hi Alice!", "Alice");
        charlie.broadcast("I'm new here!");

        // Change user states
        bob.goOffline();
        alice.send("Bob, are you there?"); // Bob shouldn't receive this
        bob.goOnline();
        charlie.sendPrivate("Welcome back!", "Bob");

        // Remove user
        chatRoom.removeUser(charlie);
        assertEquals(2, chatRoom.getTotalUsersCount());
    }
}