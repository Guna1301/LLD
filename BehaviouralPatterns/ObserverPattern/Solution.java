package BehaviouralPatterns.ObserverPattern;
import java.util.*;

// observer interface
interface Subscriber {
    void update(String videoTitle);
}


// concrete observer
class EmailSubscriber implements Subscriber{
    private String email;
    
    public EmailSubscriber(String email) {
        this.email = email;
    }
    
    @Override
    public void update(String videoTitle) {
        System.out.println("Email sent to " + email + " about new video: " + videoTitle);
    }
}


// concreate observer: mobile app subscriber
class AppSubscriber implements Subscriber{
    private String username;
    
    public AppSubscriber(String username) {
        this.username = username;
    }
    
    @Override
    public void update(String videoTitle) {
        System.out.println("Push notification sent to " + username + " about new video: " + videoTitle);
    }
}

// subject interface
interface Channel{
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers(String videoTitle);
}

// concrete subject: YouTube channel
class YouTubeChannel implements Channel{
    private List<Subscriber> subscribers;
    
    public YouTubeChannel() {
        this.subscribers = new ArrayList<>();
    }
    
    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
    
    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }
    
    @Override
    public void notifySubscribers(String videoTitle) {
        for(Subscriber subscriber : subscribers) {
            subscriber.update(videoTitle);
        }
    }
    
    public void uploadNewVideo(String title){
        // upload the video
        System.out.println("New video uploaded: " + title+"\n");

        // notify subscribers
        System.out.println("Notifying subscribers...\n");
        notifySubscribers(title);
    }
}

// client code
public class Solution {
    public static void main(String[] args) {
        YouTubeChannel dumChannel = new YouTubeChannel();
        
        dumChannel.subscribe(new EmailSubscriber("user1@example.com"));
        dumChannel.subscribe(new AppSubscriber("user2"));

        dumChannel.uploadNewVideo("Observer Pattern in Java");
    }
}

/*
probem: channel is tightly coupled with notification logic
solution: each subscriber handles its own notifcation via the update method

problem: next extension is adding new notification methods
solution: add new subscriber classes without modifying existing code

problem: no reusability of notification logic
solution: notification logic is encapsulated within subscriber classes, making it reusable

problem: violates single responsibility principle
solution: YouTubeChannel is responsible for managing subscribers and uploading videos, while each subscriber class is responsible for its own notification logic

problem: scalability issues
solution: subscribe and unsubscribe methods allow dynamic management of subscribers, making it easy to add or remove notification methods without changing the YouTubeChannel class

*/