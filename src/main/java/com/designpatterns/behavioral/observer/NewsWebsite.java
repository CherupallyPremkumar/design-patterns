package com.designpatterns.behavioral.observer;

/**
 * NewsWebsite class representing a news website subscriber.
 * Another concrete implementation of the Observer interface.
 */
public class NewsWebsite implements NewsSubscriber {
    private String websiteName;
    private String lastNews;

    public NewsWebsite(String websiteName) {
        this.websiteName = websiteName;
    }

    @Override
    public void update(String news) {
        this.lastNews = news;
        // In real implementation, this would update the website content
        System.out.println(websiteName + " updating website with: " + news);
    }

    @Override
    public String getSubscriberType() {
        return "WEBSITE";
    }

    public String getLastNews() {
        return lastNews;
    }

    public String getWebsiteName() {
        return websiteName;
    }
}