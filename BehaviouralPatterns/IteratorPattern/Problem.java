package BehaviouralPatterns.IteratorPattern;
import java.util.*;

// a simple video class with title
class Video{
    String title;

    public Video(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }
}

class YouTubePlaylist{
    private List<Video> videos = new ArrayList<>();

    // method to add video to playlist
    public void addVideo(Video video){
        videos.add(video);
    }

    // method to get all videos in the playlist
    public List<Video> getVideos(){
        return videos;
    }
}

public class Problem {
    public static void main(String[] args) {
        YouTubePlaylist playlist = new YouTubePlaylist();
        playlist.addVideo(new Video("Video 1"));
        playlist.addVideo(new Video("Video 2"));

        // Directly accessing the internal list of videos
        for(Video video : playlist.getVideos()){
            System.out.println("Playing: " + video.getTitle());
        }
    }
}

/*
issues with above code:

there are several issues with the above code:
- expose internal structure:
    - the internal list or array is directly returned via getVideos() or similar methods
    - this breaks encapsulation and exposes the internal structure of the collection
    - client can access or even modify the internal collection directly
- tight coupling:
    - the external code is tightly coupled to the specific collection used(like vector, list, etc.)
    - any change in the underlying collection would require changes in the external code(client code)
- no control over traversal:
    - traversal logci is managed outside the collection class
    - we can't enforce any specific traversal order or logic
- difficult to support multiple independant traversals:
    - if multiple clients want to traverse the collection simultaneously
    - they would need to manage their own traversal state
    - leading to complex and error-prone code

we can solve these issues using iterator pattern.

*/