package com.designpatterns.behavioral.iterator;

/**
 * Represents a song in the music playlist
 */
public class Song {
    private final String title;
    private final String artist;
    private final String genre;
    private int playCount;

    public Song(String title, String artist, String genre) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.playCount = 0;
    }

    public void incrementPlayCount() {
        this.playCount++;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getPlayCount() {
        return playCount;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", artist, title, genre);
    }
}