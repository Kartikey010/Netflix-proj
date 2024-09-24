package com.cache.Netflix;



public class Main {
    public static void main(String[] args) {
        MediaService mediaService = new MediaService();

        // Create two user objects
        User user1 = new User("john_doe", "Premium");
        User user2 = new User("jane_doe", "Standard");

        // Add media to user1's cache and disk
        MediaContent media1 = new MediaContent("Stranger Things", "Sci-Fi", 50);
        MediaContent media2 = new MediaContent("Money Heist", "Thriller", 45);

        mediaService.addMedia(user1, media1);
        mediaService.addMedia(user1, media2);

        // Simulate fetching media
        System.out.println("\n" + user1.getUsername() + " is watching:");
        mediaService.fetchMedia(user1, "Stranger Things");
        mediaService.fetchMedia(user1, "Money Heist");

        // Simulate fetching for user2 from disk
        System.out.println("\n" + user2.getUsername() + " is watching (fetch from disk):");
        mediaService.fetchMedia(user2, "Stranger Things");
    }
}



// public class Main {
//     public static void main(String[] args) {
//         MediaService mediaService = new MediaService();

//         // Create two users, each with their own cache
//         User user1 = new User("john_doe", "Premium");
//         User user2 = new User("jane_doe", "Basic");

//         // Simulate user1 requests
//         System.out.println(user1.getUsername() + " is watching:");
//         mediaService.fetchMedia(user1, "Stranger Things");
//         mediaService.fetchMedia(user1, "Money Heist");

//         // Fetch again for user1 to trigger cache
//         System.out.println("\nSecond request (should be fetched from user1's cache):");
//         mediaService.fetchMedia(user1, "Stranger Things");

//         // Simulate user2 requests
//         System.out.println("\n" + user2.getUsername() + " is watching:");
//         mediaService.fetchMedia(user2, "Breaking Bad");
//         mediaService.fetchMedia(user2, "Friends");

//         // Fetch again for user2 to trigger cache
//         System.out.println("\nSecond request (should be fetched from user2's cache):");
//         mediaService.fetchMedia(user2, "Breaking Bad");
//     }
// }