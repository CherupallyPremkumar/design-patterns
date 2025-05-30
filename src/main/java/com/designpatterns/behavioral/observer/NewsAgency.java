package com.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * NewsAgency class acting as the Subject (Observable) in the Observer pattern.
 * This demonstrates how to implement a publisher that notifies multiple
 * subscribers.
 * 
 * Key points for interviews:
 * 1. Maintains list of observers
 * 2. Provides methods to attach/detach observers
 * 3. Notifies all observers when state changes
 * 4. Loose coupling between subject and observers
 */
public class NewsAgency {
    private String news;
    private List<NewsSubscriber> subscribers = new ArrayList<>();

    public void attach(NewsSubscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    public void detach(NewsSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers() {
        for (NewsSubscriber subscriber : subscribers) {
            subscriber.update(news);
        }
    }

    public void setNews(String news) {
        this.news = news;
        notifySubscribers();
    }

    public String getNews() {
        return news;
    }

    public int getSubscriberCount() {
        return subscribers.size();
    }
}