package BehaviouralPatterns.MediatorPattern;

import java.util.*;

// Mediator interface
interface DocumentSessionMediator {
    void join(User user);
    void sendChangeNotification(User fromUser, String change);
}

// Concrete Mediator
class CollaborativeDocument implements DocumentSessionMediator{
    private List<User> users;

    public CollaborativeDocument() {
        this.users = new ArrayList<>();
    }

    @Override
    public void join(User user) {
        users.add(user);
    }

    @Override
    public void sendChangeNotification(User fromUser, String change) {
        for (User user : users) {
            if (user!=fromUser) { // notify all users except the one who made the change
                user.receiveChangeNotification(fromUser, change);
            }
        }
    }
}

// User class (Colleague)
class User {
    private String name;
    private DocumentSessionMediator mediator;

    public User(String name, DocumentSessionMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    public void makeChange(String change) {
        System.out.println(name + " made a change: " + change);
        mediator.sendChangeNotification(this, change); // notify the mediator of the change
    }

    public void receiveChangeNotification(User fromUser, String change) {
        System.out.println(name + " received a change notification from " + fromUser.getName() + ": " + change);
    }
}


public class Solution {
    public static void main(String[] args) {
        CollaborativeDocument document = new CollaborativeDocument();

        // create users and join the document session
        User alice = new User("Alice", document);
        User bob = new User("Bob", document);
        User charlie = new User("Charlie", document);

        // users join the document session
        document.join(alice);
        document.join(bob);
        document.join(charlie);

        // Alice makes a change to the document, and both Bob and Charlie will be notified
        alice.makeChange("Added introduction section.");

        // Bob makes a change to the document, and both Alice and Charlie will be notified
        bob.makeChange("Added conclusion section.");

        // Charlie makes a change to the document, and both Alice and Bob will be notified
        charlie.makeChange("Added references section.");
    }
}


/*
changes made to solve previous issues:
1. Introduced a Mediator interface (DocumentSessionMediator) to define the contract for communication between users.
2. Created a Concrete Mediator (CollaborativeDocument) that implements the Mediator interface and manages the communication between users.
3. Modified the User class to interact with the Mediator instead of directly managing collaborators. Users now join the document session through the Mediator and send change notifications to the Mediator, which then forwards them to other users.
4. This design decouples the User class from other User instances, as they no longer need to maintain a list of collaborators or directly notify each other. Instead, they rely on the Mediator to handle communication, which promotes loose coupling and better separation of concerns.
5. The Mediator pattern also allows for easier scalability, as adding new users or changing the collaboration structure can be done by modifying the Mediator without affecting the User class.
6. user class now interacts only with collaborative document(mediator), and not with other users directly. This reduces coupling and makes the system more flexible and maintainable.
*/

/*
how this solves previous issues:

problem: tight coupling bwt users
solution: users no longer directly manage their collaborators or notify each other. Instead, they interact with the Mediator (CollaborativeDocument) to join the session and send change notifications. This decouples the User class from other User instances, as they no longer need to maintain a list of collaborators or directly notify each other.

problem: adding/removing users breaks the structure
solution: the Mediator (CollaborativeDocument) manages the list of users and their interactions. When a user joins the document session, they simply call the join method on the Mediator, which adds them to the list of users. This allows for easy addition or removal of users without affecting the User class or other users.

problem: hard to orchestrate roles(e.g., who can edit, who can view)
solution: the Mediator can be extended to include role management and access control. For example, the CollaborativeDocument can maintain a list of users with different roles (e.g., editors, viewers) and implement logic to determine who can make changes and who can only view the document. This centralizes the role management and makes it easier to enforce access control without modifying the User class.

problem: code duplication in notification logic
solution: the notification logic is centralized in the Mediator (CollaborativeDocument). When a user makes a change, the Mediator forwards the notification to all other users.

problem: lack of seperation of concerns
solution: the User class is now focused solely on representing a user and their interactions with the document

problem: scalability issues with managing collaborators and notifications
solution: the Mediator (CollaborativeDocument) manages the list of users and their interactions, which allows for easier scalability as the number of users increases. The Mediator can efficiently handle notifications and interactions between users without requiring each user to manage their own list of collaborators.

*/