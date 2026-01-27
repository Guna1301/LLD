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

interface Playlist{
    PlaylistIterator createIterator();
}


class YouTubePlaylist implements Playlist{
    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video){
        videos.add(video);
    }

    public PlaylistIterator createIterator(){
        return new YouTubePlaylistIterator(videos);
    }
}

interface PlaylistIterator{
    boolean hasNext();
    Video next();
}

class YouTubePlaylistIterator implements PlaylistIterator{
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

public class BetterSolution {
    public static void main(String[] args) {
        YouTubePlaylist playlist = new YouTubePlaylist();
        playlist.addVideo(new Video("something"));
        playlist.addVideo(new Video("another thing"));

        PlaylistIterator iterator = playlist.createIterator();
        while(iterator.hasNext()){
            Video video = iterator.next();
            System.out.println("Playing: " + video.getTitle());
        }
    }
}

/*
key improvements in the above code using iterator pattern:
- the youtubeplaylist class no longer exposes its internal implementation of videos
- the client does not manage or know about the internal structure of the collection
- the playlist interface allows us to make other types of playlists(e.g., musicplaylist) that 
    can also be iterated in the same way
- fully align with iterator design pattern principles

*/