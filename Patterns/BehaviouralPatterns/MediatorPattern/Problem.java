package BehaviouralPatterns.MediatorPattern;

import java.util.*;


class User{
    private String name;
    private List<User> others; // list of users that have access to this user

    // constructor
    public User(String name) {
        this.name = name;
        this.others = new ArrayList<>();
    }

    // method to add a collaborator to this user (i.e., give access to this user)
    public void addCollaborator(User user) {
        others.add(user);
        user.others.add(this); // also add this user to the collaborator's list of others
    }

    // method to make change to the document and notify collaborators
    // each collaborator will receive the change notification
    public void makeChange(String change) {
        System.out.println(name + " made a change: " + change);
        notifyCollaborators(change);
    }

    // method to notify collaborators of the change
    private void notifyCollaborators(String change) {
        for (User user : others) {
            user.receiveChangeNotification(name, change);
        }
    }

    // method to receive change notification from another user
    public void receiveChangeNotification(String fromUser, String change) {
        System.out.println(name + " received a change notification from " + fromUser + ": " + change);
    }
}

public class Problem {
    public static void main(String[] args) {
        // create users
        User alice = new User("Alice");
        User bob = new User("Bob");
        User charlie = new User("Charlie");

        // set up collaborators
        alice.addCollaborator(bob); // Bob has access to Alice's document
        alice.addCollaborator(charlie); // Charlie has access to Alice's document

        // Alice makes a change to the document, and both Bob and Charlie will be notified
        alice.makeChange("Added introduction section.");

        // Bob makes a change to the document, but only Alice will be notified, not Bob and Charlie
        bob.makeChange("Added conclusion section.");
    }   
}


/*
problems with this approach:
1. Tight coupling: Users are directly aware of each other and manage their own list of collaborators. 
    This creates tight coupling between users, making it difficult to change the collaboration structure without modifying the User class.

2. Scalability issues: As the number of users increases, managing the list of collaborators and notifications becomes more complex and error-prone.

3. Lack of central control: There is no central mediator to manage the interactions between users. 
    This can lead to inconsistent behavior and makes it harder to implement additional features like access control or logging. 

4. Code duplication: Each user is responsible for notifying their collaborators, which can lead to code duplication and makes it harder to maintain the notification logic.

5. Difficulty in adding new features: If we want to add new features like logging changes, implementing access control, or adding more complex notification logic, we would need to modify the User class directly, which can lead to bugs and maintenance issues.

6. Testing challenges: Testing the interactions between users can be more difficult because the logic is spread across multiple User instances, making it harder to isolate and test specific behaviors.

7. Violation of Single Responsibility Principle: The User class is responsible for both managing its collaborators and handling notifications, which violates the Single Responsibility Principle and can lead to a more complex and less maintainable codebase.

8. Difficulty in handling complex interactions: If the interactions between users become more complex (e.g., different types of notifications, conditional notifications), the User class would need to be modified to handle these cases, leading to a more complex and less maintainable codebase.

9. adding/removing users breaks the system: If a user is added or removed from the collaboration, it can lead to issues with notifications and access control, as the User class is responsible for managing its own collaborators. This can lead to bugs and maintenance issues when users are added or removed from the system.


*/