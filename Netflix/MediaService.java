package com.cache.Netflix;
import java.io.*;
import java.nio.file.*;


public class MediaService {

    private static final String DISK_STORAGE_PATH = "E:\\Mthree\\caching_project\\my-caching-project\\src\\main\\java\\com\\cache\\Netflix\\directory"; // Directory for disk storage

    public MediaService() {
        // Initialize disk storage directory if it doesn't exist
        File storageDir = new File(DISK_STORAGE_PATH);
        if (!storageDir.exists()) {
            storageDir.mkdir();
        }
    }

    // Fetch media for a specific user using their individual cache
    public MediaContent fetchMedia(User user, String title) {
        UserCache userCache = user.getUserCache();

        // Check if the media is in the user's cache
        if (userCache.isInCache(title)) {
            System.out.println("Fetching '" + title + "' from " + user.getUsername() + "'s cache.");
            return userCache.getMediaFromCache(title);
        }

        // If not in cache, fetch from disk
        System.out.println("Fetching '" + title + "' from disk for " + user.getUsername() + ".");
        MediaContent media = fetchMediaFromDisk(title);

        // If found on disk, add to cache
        if (media != null) {
            userCache.addMediaToCache(media);
            return media;
        }

        System.out.println("Media not found on disk or cache for " + user.getUsername());
        return null;
    }

    // Write media content to disk (Serialization)
    private void saveMediaToDisk(MediaContent media) throws IOException {
        Path filePath = Paths.get(DISK_STORAGE_PATH, media.getTitle());
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(filePath))) {
            out.writeObject(media);
        }
    }

    // Read media content from disk (Deserialization)
    private MediaContent fetchMediaFromDisk(String title) {
        Path filePath = Paths.get(DISK_STORAGE_PATH, title);
        if (Files.exists(filePath)) {
            try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(filePath))) {
                return (MediaContent) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error reading media from disk: " + e.getMessage());
            }
        }
        return null;
    }

    // Add media to cache and disk if not already present
    public void addMedia(User user, MediaContent media) {
        UserCache userCache = user.getUserCache();

        // Add to cache if not already present
        if (!userCache.isInCache(media.getTitle())) {
            userCache.addMediaToCache(media);
            System.out.println("Added '" + media.getTitle() + "' to " + user.getUsername() + "'s cache.");
        }

        // Add to disk as well
        try {
            if (fetchMediaFromDisk(media.getTitle()) == null) {
                saveMediaToDisk(media);
                System.out.println("Added '" + media.getTitle() + "' to disk storage.");
            }
        } catch (IOException e) {
            System.out.println("Error saving media to disk: " + e.getMessage());
        }
    }
}



// import java.util.ArrayList;
// import java.util.List;

// public class MediaService {
    
//     public MediaContent fetchMedia(User user, String title) {
//         // Check if the media is in the user's cache
//         if (user.getUserCache().isInCache(title)) {
//             System.out.println("Fetching '" + title + "' from " + user.getUsername() + "'s cache.");
//             return user.getUserCache().getMediaFromCache(title);
//         }

//         // Simulate a database or API call to fetch media content
//         System.out.println("Fetching '" + title + "' from database/API for " + user.getUsername() + ".");
//         MediaContent media = fetchMediaFromDatabase(title);

//         // Add the media to the user's cache
//         if (media != null) {
//             user.getUserCache().addMediaToCache(media);
//         }
//         return media;
//     }

//     private MediaContent fetchMediaFromDatabase(String title) {
//         // Simulate data source with hardcoded media list
//         List<MediaContent> mediaList = new ArrayList<>();
//         mediaList.add(new MediaContent("Stranger Things", "Sci-Fi", 50));
//         mediaList.add(new MediaContent("The Crown", "Drama", 60));
//         mediaList.add(new MediaContent("Money Heist", "Thriller", 45));
//         mediaList.add(new MediaContent("Breaking Bad", "Crime", 55));
//         mediaList.add(new MediaContent("Friends", "Comedy", 30));

//         // Find media by title
//         for (MediaContent media : mediaList) {
//             if (media.getTitle().equalsIgnoreCase(title)) {
//                 return media;
//             }
//         }
//         return null;
//     }
// }