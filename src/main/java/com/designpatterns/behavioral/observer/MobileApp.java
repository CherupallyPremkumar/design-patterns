package com.designpatterns.behavioral.observer;

/**
 * MobileApp class representing a mobile application subscriber.
 * Another concrete implementation of the Observer interface.
 */
public class MobileApp implements NewsSubscriber {
    private String appName;
    private String lastNews;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String news) {
        this.lastNews = news;
        // In real implementation, this would send push notifications
        System.out.println(appName + " sending push notification: " + news);
    }

    @Override
    public String getSubscriberType() {
        return "MOBILE_APP";
    }

    public String getLastNews() {
        return lastNews;
    }

    public String getAppName() {
        return appName;
    }
}