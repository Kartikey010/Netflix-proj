package com.cache.Netflix;

public class User {
    private String username;
    private String subscriptionPlan;
    private UserCache userCache; // Each user has their own cache

    public User(String username, String subscriptionPlan) {
        this.username = username;
        this.subscriptionPlan = subscriptionPlan;
        this.userCache = new UserCache(); // Initialize a cache for each user
    }

    public String getUsername() {
        return username;
    }

    public String getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public UserCache getUserCache() {
        return userCache;
    }
}