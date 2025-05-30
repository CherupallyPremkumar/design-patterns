package com.designpatterns.behavioral.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating Observer pattern usage and verification.
 * Shows common interview scenarios and verification points.
 */
public class NewsAgencyTest {

    private NewsAgency newsAgency;
    private NewsChannel channel;
    private NewsWebsite website;
    private MobileApp mobileApp;

    @BeforeEach
    void setUp() {
        newsAgency = new NewsAgency();
        channel = new NewsChannel("CNN");
        website = new NewsWebsite("Reuters.com");
        mobileApp = new MobileApp("BBC News App");
    }

    @Test
    void testAttachSubscriber() {
        newsAgency.attach(channel);
        assertEquals(1, newsAgency.getSubscriberCount());

        // Attaching same subscriber twice should not increase count
        newsAgency.attach(channel);
        assertEquals(1, newsAgency.getSubscriberCount());
    }

    @Test
    void testDetachSubscriber() {
        newsAgency.attach(channel);
        newsAgency.attach(website);
        assertEquals(2, newsAgency.getSubscriberCount());

        newsAgency.detach(channel);
        assertEquals(1, newsAgency.getSubscriberCount());
    }

    @Test
    void testNotifySubscribers() {
        newsAgency.attach(channel);
        newsAgency.attach(website);
        newsAgency.attach(mobileApp);

        String newsUpdate = "Breaking: Important news!";
        newsAgency.setNews(newsUpdate);

        // Verify all subscribers received the update
        assertEquals(newsUpdate, channel.getLastNews());
        assertEquals(newsUpdate, website.getLastNews());
        assertEquals(newsUpdate, mobileApp.getLastNews());
    }

    @Test
    void testSubscriberTypes() {
        assertEquals("TV_CHANNEL", channel.getSubscriberType());
        assertEquals("WEBSITE", website.getSubscriberType());
        assertEquals("MOBILE_APP", mobileApp.getSubscriberType());
    }

    @Test
    void testMultipleUpdates() {
        newsAgency.attach(channel);
        newsAgency.attach(website);

        String firstNews = "First update";
        String secondNews = "Second update";

        newsAgency.setNews(firstNews);
        assertEquals(firstNews, channel.getLastNews());
        assertEquals(firstNews, website.getLastNews());

        newsAgency.setNews(secondNews);
        assertEquals(secondNews, channel.getLastNews());
        assertEquals(secondNews, website.getLastNews());
    }

    @Test
    void testLateSubscriber() {
        String firstNews = "First update";
        newsAgency.setNews(firstNews);

        // Late subscriber
        newsAgency.attach(channel);

        String secondNews = "Second update";
        newsAgency.setNews(secondNews);

        // Late subscriber should only receive updates after attachment
        assertEquals(secondNews, channel.getLastNews());
    }

    @Test
    void testDetachedSubscriberDoesNotReceiveUpdates() {
        newsAgency.attach(channel);

        String firstNews = "First update";
        newsAgency.setNews(firstNews);
        assertEquals(firstNews, channel.getLastNews());

        newsAgency.detach(channel);

        String secondNews = "Second update";
        newsAgency.setNews(secondNews);

        // Detached subscriber should not receive new updates
        assertEquals(firstNews, channel.getLastNews());
    }
}