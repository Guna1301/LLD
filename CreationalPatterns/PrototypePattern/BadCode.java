package CreationalPatterns.PrototypePattern;
import java.util.*;

interface EmailTemplate{
    void setContent(String content);
    void send(String to);
}

class WelcomeEmail implements EmailTemplate{
    private String subject;
    private String content;

    public WelcomeEmail(){
        this.subject = "Welcome to Our Service!";
        this.content = "Dear User, Welcome to our service. We are glad to have you.";
    }

    @Override
    public void setContent(String content){
        this.content = content;
    }

    @Override
    public void send(String to){
        System.out.println("Sending to "+to+": "+subject+"\n"+content);
    }
}

public class BadCode {
    public static void main(String[] args) {
        WelcomeEmail email1 = new WelcomeEmail();
        email1.send("user1@gmail.com");

        WelcomeEmail email2 = new WelcomeEmail();
        email2.setContent("Dear User, Welcome aboard! We hope you enjoy our service."); 
        email2.send("user2@gmail.com");

        WelcomeEmail email3 = new WelcomeEmail();
        email3.setContent("Dear User, Thank you for joining us! We are excited to have you."); 
        email3.send("user3@gmail.com");
    }
}

/*
    issues with the above code:
1. Tight coupling to conrete class:
    - the code uses the welcomeemail class directly, making it difficult to introduce new email types without modifying existing code.
    - no abstraction for cloning-client code is tightly bound to object creating logic
2. Repetitive object creation logic:
    - each time a new email is needed, a new instance of welcomeemail is created and initialized from scratch.
    - this leads to code duplication and makes it harder to maintain.
3. violates DRY (Don't Repeat Yourself) principle:
    - the initialization logic for welcomeemail is repeated every time a new email is created.
    - any changes to the initialization process would require modifications in multiple places.
4. No cloning mechanism:
    - the code lacks a cloning mechanism to create new email instances based on existing ones.
    - this results in inefficient object creation and increased complexity when dealing with multiple email types.
*/
