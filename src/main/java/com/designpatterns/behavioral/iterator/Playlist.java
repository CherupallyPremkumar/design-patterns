package com.designpatterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Aggregate class that manages the song collection and provides different
 * iterators
 */
public class Playlist {
    private final List<Song> songs;
    private final String name;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public String getName() {
        return name;
    }

    public int getSongCount() {
        return songs.size();
    }

    // Regular sequential iterator
    public PlaylistIterator getIterator() {
        return new SequentialIterator();
    }

    // Shuffle iterator
    public PlaylistIterator getShuffleIterator() {
        return new ShuffleIterator();
    }

    // Genre-based iterator
    public PlaylistIterator getGenreIterator(String genre) {
        return new GenreIterator(genre);
    }

    // Most played songs iterator
    public PlaylistIterator getMostPlayedIterator() {
        return new MostPlayedIterator();
    }

    // Sequential Iterator Implementation
    private class SequentialIterator implements PlaylistIterator {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < songs.size();
        }

        @Override
        public Song next() {
            if (hasNext()) {
                return songs.get(currentIndex++);
            }
            return null;
        }

        @Override
        public void reset() {
            currentIndex = 0;
        }
    }

    // Shuffle Iterator Implementation
    private class ShuffleIterator implements PlaylistIterator {
        private final List<Song> shuffledSongs;
        private int currentIndex = 0;

        ShuffleIterator() {
            shuffledSongs = new ArrayList<>(songs);
            Collections.shuffle(shuffledSongs, new Random());
        }

        @Override
        public boolean hasNext() {
            return currentIndex < shuffledSongs.size();
        }

        @Override
        public Song next() {
            if (hasNext()) {
                return shuffledSongs.get(currentIndex++);
            }
            return null;
        }

        @Override
        public void reset() {
            Collections.shuffle(shuffledSongs, new Random());
            currentIndex = 0;
        }
    }

    // Genre Iterator Implementation
    private class GenreIterator implements PlaylistIterator {
        private final String genre;
        private int currentIndex = 0;

        GenreIterator(String genre) {
            this.genre = genre;
        }

        @Override
        public boolean hasNext() {
            for (int i = currentIndex; i < songs.size(); i++) {
                if (songs.get(i).getGenre().equals(genre)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Song next() {
            while (currentIndex < songs.size()) {
                Song song = songs.get(currentIndex++);
                if (song.getGenre().equals(genre)) {
                    return song;
                }
            }
            return null;
        }

        @Override
        public void reset() {
            currentIndex = 0;
        }
    }

    // Most Played Iterator Implementation
    private class MostPlayedIterator implements PlaylistIterator {
        private final List<Song> sortedSongs;
        private int currentIndex = 0;

        MostPlayedIterator() {
            sortedSongs = new ArrayList<>(songs);
            sortedSongs.sort((s1, s2) -> s2.getPlayCount() - s1.getPlayCount());
        }

        @Override
        public boolean hasNext() {
            return currentIndex < sortedSongs.size();
        }

        @Override
        public Song next() {
            if (hasNext()) {
                return sortedSongs.get(currentIndex++);
            }
            return null;
        }

        @Override
        public void reset() {
            sortedSongs.sort((s1, s2) -> s2.getPlayCount() - s1.getPlayCount());
            currentIndex = 0;
        }
    }
}