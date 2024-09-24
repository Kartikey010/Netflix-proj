package com.cache.Netflix;


import java.io.Serializable;

public class MediaContent implements Serializable {
    private static final long serialVersionUID = 1L;  // Added for serialization
    private String title;
    private String genre;
    private int duration;

    public MediaContent(String title, String genre, int duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "MediaContent{title='" + title + "', genre='" + genre + "', duration=" + duration + "}";
    }
}

// public class MediaContent {
//     private String title;
//     private String genre;
//     private int duration; // duration in minutes

//     public MediaContent(String title, String genre, int duration) {
//         this.title = title;
//         this.genre = genre;
//         this.duration = duration;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public String getGenre() {
//         return genre;
//     }

//     public int getDuration() {
//         return duration;
//     }

//     @Override
//     public String toString() {
//         return "Title: " + title + ", Genre: " + genre + ", Duration: " + duration + " minutes";
//     }
// }