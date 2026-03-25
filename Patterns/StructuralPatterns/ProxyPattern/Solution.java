package StructuralPatterns.ProxyPattern;
import java.util.HashMap;

interface VideoDownloader{
    String downloadVideo(String videoUrl);
}


class RealVideoDownloader implements VideoDownloader{
    public String downloadVideo(String videoUrl){
        System.out.println("Downloading video from: " + videoUrl);
        String content = "Video content of " + videoUrl;
        System.out.println("downloaded content: " + content);
        return content;
    }
}

class CachedVideoDownloader implements VideoDownloader{
    private RealVideoDownloder realDownloader;
    private HashMap<String, String> cache;

    public CachedVideoDownloader(){
        this.realDownloader = new RealVideoDownloder();
        this.cache = new HashMap<>();
    }

    @Override
    public String downloadVideo(String videoUrl){
        if(cache.containsKey(videoUrl)){
            System.out.println("Fetching video from cache: " + videoUrl);
            return cache.get(videoUrl);
        } else {
            String content = realDownloader.downloadVideo(videoUrl);
            cache.put(videoUrl, content);
            return content;
        }
    }
}




public class Solution {
    public static void main(String[] args) {
        VideoDownloader downloader = new CachedVideoDownloader();

        System.out.println("user 1 downloading video:");
        downloader.downloadVideo("http://example.com/video1");

        System.out.println("\nuser 2 downloading video:");
        downloader.downloadVideo("http://example.com/video1");
    }
}


/*
how proxy patterns solves issues:

in this example proxy(CachedVideoDownloader) controls access to the real object(RealVideoDownloader) 
and adds caching functionality without modifying the real object's code.

this way, the real object is access only when absolutely necessary, 
reducing redundant downloads and improving performance.

*/