package com.cache.Netflix;
import java.util.HashMap;

public class UserCache {
    private HashMap<String, MediaContent> mediaCache;
    private final int MAX_CACHE_SIZE = 5;

    public UserCache() {
        mediaCache = new HashMap<>();
    }

    public MediaContent getMediaFromCache(String title) {
        return mediaCache.getOrDefault(title, null);
    }

    public void addMediaToCache(MediaContent media) {
        if (mediaCache.size() >= MAX_CACHE_SIZE) {
            // Simple eviction strategy: remove the first entry (FIFO)
            String oldestTitle = mediaCache.keySet().iterator().next();
            mediaCache.remove(oldestTitle);
        }
        mediaCache.put(media.getTitle(), media);
    }

    public boolean isInCache(String title) {
        return mediaCache.containsKey(title);
    }
}