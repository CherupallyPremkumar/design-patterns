package com.designpatterns.behavioral.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class PlaylistTest {
    private Playlist playlist;
    private Song song1, song2, song3, song4;

    @BeforeEach
    void setUp() {
        playlist = new Playlist("Test Playlist");

        song1 = new Song("Shape of You", "Ed Sheeran", "Pop");
        song2 = new Song("Master of Puppets", "Metallica", "Rock");
        song3 = new Song("Blinding Lights", "The Weeknd", "Pop");
        song4 = new Song("Sweet Child O' Mine", "Guns N' Roses", "Rock");

        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);
        playlist.addSong(song4);

        // Add some play counts
        song1.incrementPlayCount(); // 1 play
        song2.incrementPlayCount();
        song2.incrementPlayCount(); // 2 plays
        song3.incrementPlayCount();
        song3.incrementPlayCount();
        song3.incrementPlayCount(); // 3 plays
    }

    @Test
    void testSequentialIterator() {
        PlaylistIterator iterator = playlist.getIterator();
        assertTrue(iterator.hasNext());
        assertEquals(song1, iterator.next());
        assertEquals(song2, iterator.next());
        assertEquals(song3, iterator.next());
        assertEquals(song4, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testShuffleIterator() {
        PlaylistIterator iterator = playlist.getShuffleIterator();
        Set<Song> songs = new HashSet<>();

        // Collect all songs from shuffle
        while (iterator.hasNext()) {
            songs.add(iterator.next());
        }

        // Verify all songs are present
        assertEquals(4, songs.size());
        assertTrue(songs.contains(song1));
        assertTrue(songs.contains(song2));
        assertTrue(songs.contains(song3));
        assertTrue(songs.contains(song4));

        // Test reset and reshuffling
        iterator.reset();
        assertTrue(iterator.hasNext());
    }

    @Test
    void testGenreIterator() {
        PlaylistIterator iterator = playlist.getGenreIterator("Pop");
        Set<Song> popSongs = new HashSet<>();

        while (iterator.hasNext()) {
            Song song = iterator.next();
            assertEquals("Pop", song.getGenre());
            popSongs.add(song);
        }

        assertEquals(2, popSongs.size());
        assertTrue(popSongs.contains(song1));
        assertTrue(popSongs.contains(song3));
    }

    @Test
    void testMostPlayedIterator() {
        PlaylistIterator iterator = playlist.getMostPlayedIterator();

        assertEquals(song3, iterator.next()); // 3 plays
        assertEquals(song2, iterator.next()); // 2 plays
        assertEquals(song1, iterator.next()); // 1 play
        assertEquals(song4, iterator.next()); // 0 plays
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorReset() {
        PlaylistIterator iterator = playlist.getIterator();

        // Consume all elements
        while (iterator.hasNext()) {
            iterator.next();
        }
        assertFalse(iterator.hasNext());

        // Reset and verify we can iterate again
        iterator.reset();
        assertTrue(iterator.hasNext());
        assertEquals(song1, iterator.next());
    }

    @Test
    void testPlaylistModification() {
        Song newSong = new Song("New Song", "New Artist", "Jazz");
        playlist.addSong(newSong);

        PlaylistIterator iterator = playlist.getIterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        assertEquals(5, count);

        playlist.removeSong(newSong);
        iterator.reset();
        count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        assertEquals(4, count);
    }

    @Test
    void testEmptyPlaylist() {
        Playlist emptyPlaylist = new Playlist("Empty");
        PlaylistIterator iterator = emptyPlaylist.getIterator();

        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }
}