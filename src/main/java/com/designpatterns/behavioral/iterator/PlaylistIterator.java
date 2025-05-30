package com.designpatterns.behavioral.iterator;

/**
 * Custom iterator interface for playlist iteration
 */
public interface PlaylistIterator {
    boolean hasNext();

    Song next();

    void reset();
}