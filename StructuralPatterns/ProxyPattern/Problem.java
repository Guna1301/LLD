package StructuralPatterns.ProxyPattern;
/* scenario where we want to download a video mutiple times, from diffeent place in the 
code or by different users. a poor design look like this */

class RealVideoDownloder{
    // caching logic missing
    // filtering logic missing
    // access control logic missing
    public String downloadVideo(String videoUrl){
        System.out.println("Downloading video from: " + videoUrl);
        String content = "Video content of " + videoUrl;
        System.out.println("downloaded content: " + content);
        return content;
    }
}

public class Problem {
    public static void main(String[] args) {
        System.out.println("user 1 downloading video:");
        RealVideoDownloder downloader = new RealVideoDownloder();
        downloader.downloadVideo("http://example.com/video1");

        System.out.println("\nuser 2 downloading video:");
        downloader.downloadVideo("http://example.com/video1");
    }
}

/* 
underatdning the issues:
- no caching: each time a video is requested, it is downloaded from the source, leading to redundant downloads and increased bandwidth usage.
- no access control: any user can download any video without restrictions, which may lead to unauthorized access.
- the client directly depends on the RealVideoDownloader, making it difficult to add additional functionalities like caching or access control without modifying the client code.
- it results in multiple objects being created for the same video download request, leading to inefficiencies.

here if we add mutiple logics like caching, access control, filtering etc in the RealVideoDownloader class, 
it will violates srp(single responsibility principle) as the class will have more than one reason to change.
*/