package BehaviouralPatterns.ObserverPattern;

class YouTubeChannel{
    public void uploadNewVideo(String title){
        // upload the video
        System.out.println("New video uploaded: " + title+"\n");

        // notify subscribers
        System.out.println("Notifying subscribers...\n");
        System.out.println("pusing in app notification to subscribers\n");
    }
}

public class Problem {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel();
        channel.uploadNewVideo("Observer Pattern in Java");
    }
}
/*
problems with this approach:
- tightly coupled code:
    yt channel class is tightly coupled with notification logic, if tomorrow we want to send an sms or push notification, we have to modify the YouTubeChannel class.
- no reusability:
    we cannot reuse the notification logic in other parts of the application.
- scalability issues:
    as the application grows, adding new notification methods will make the YouTubeChannel class bulky and hard to maintain.
- violates single responsibility principle:
    the YouTubeChannel class is responsible for both uploading videos and notifying subscribers, which goes against the single responsibility principle.

*/