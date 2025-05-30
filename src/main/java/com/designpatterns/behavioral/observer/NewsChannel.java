package com.designpatterns.behavioral.observer;

/**
 * NewsChannel class representing a TV news channel subscriber.
 * Concrete implementation of the Observer interface.
 */
public class NewsChannel implements NewsSubscriber {
    private String channelName;
    private String lastNews;

    public NewsChannel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void update(String news) {
        this.lastNews = news;
        // In real implementation, this would broadcast the news on TV
        System.out.println(channelName + " broadcasting: " + news);
    }

    @Override
    public String getSubscriberType() {
        return "TV_CHANNEL";
    }

    public String getLastNews() {
        return lastNews;
    }

    public String getChannelName() {
        return channelName;
    }
}