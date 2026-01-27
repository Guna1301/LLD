package BehaviouralPatterns.IteratorPattern;

import java.util.*;

class Video{
    private String title;
    public Video(String title) {
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}

class YouTubePlaylist{
    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video){
        videos.add(video);
    }

    public List<Video> getVideos(){
        return videos;
    }
}


interface PlayListIterator{
    boolean hasNext();
    Video next();
}

class YouTubePlaylistIterator implements PlayListIterator{
    private List<Video> videos;
    private int position ;

    public YouTubePlaylistIterator(List<Video> videos) {
        this.videos = videos;
        this.position = 0;
    }

    public boolean hasNext() {
        return position < videos.size();
    }

    public Video next(){
        return hasNext()? videos.get(position++) : null;
    }
}

public class Solution {
    public static void main(String[] args){
        YouTubePlaylist playlist = new YouTubePlaylist();
        playlist.addVideo(new Video("something"));
        playlist.addVideo(new Video("another thing"));

        PlayListIterator iterator = new YouTubePlaylistIterator(playlist.getVideos());
        while(iterator.hasNext()){
            Video video = iterator.next();
            System.out.println("Playing: " + video.getTitle());
        }
    }
}

/*
how this solves previous issues:

problem: direct access to internal structure
solution: colletion no longer exposes its internal data directly for traversal. insted iterator is used to access elemts one by one, encapsulating the traversal logic.

problem: no standard way to traverse
solution: all traversal is now hadnled through consistent interface(hasnext()/next()), regardless of
          the underlying collection type.

problem: traversal logic spread across client code
solution: the main logic for maintaining iteration state is encapsulated within the iterator class itself, keeping the client code clean and focused on its primary task.

problem: difficult to customize traversal
solution: custom iterator classes can be easily extended to provide different traversal strategies, without changing the underlying collection or client code.

problem: tight coupling between client and collection
solution: client code depends only on the iterator interface, reducing coupling to specific collection implementations.

*/

/*
here still one major issue still remains:
even we abstracted the traversal logic into an iterator class, the client is still responsible for creating and using the iterator, which is not ideal.
our goal of true encapsulation is to hide even the creation of the iterator from the client.
*/