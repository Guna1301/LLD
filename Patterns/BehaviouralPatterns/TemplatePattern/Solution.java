
// abstract class for defining the template method and common steps
abstract class NotificationSender{
    public final void sendNotification(String to, String message){
        
        // common logic
        rateLimitCheck(to);
        validateRecipient(to);
        String formated = formatMessage(message);
        logBeforeSend(formated, to);

        // specific logic: defined by subclasses
        String composedmessage = composeMessage(formated, to);
        sendMessage(to, composedmessage);

        // optional hook method for analytics
        updateAnalytics(to);
    }

    // common steps with default implementations
    private void rateLimitCheck(String to){
        System.out.println("checking ratelimit for : "+to);
    }
    private void validateRecipient(String to){
        System.out.println("validating recipient : "+to);
    }
    private String formatMessage(String message){
        return message.trim().toLowerCase();
    }
    private void logBeforeSend(String formated, String to){
        System.out.println("logging before send: "+formated+ " to "+to);
    }

    // abstract methods for specific steps to be implemented by subclasses
    protected abstract String composeMessage(String formated, String to);
    protected abstract void sendMessage(String to, String composedmessage);

    // optional hook method for analytics, can be overridden by subclasses if needed
    protected void updateAnalytics(String to){
        System.out.println("Analytics updated for notification sent to : "+to);
    }
}

// concrete implementation for email notifications
class EmailNotificationSender extends NotificationSender{
    @Override
    protected String composeMessage(String formated, String to) {
        return "Dear "+to+",\n"+formated;
    }

    @Override
    protected void sendMessage(String to, String composedmessage) {
        System.out.println("sending email : "+composedmessage);
    }
}

// concrete implementation for SMS notifications
class SMSNotificationSender extends NotificationSender{
    @Override
    protected String composeMessage(String formated, String to) {
        return "Dear "+to+",\n"+formated;
    }

    @Override
    protected void sendMessage(String to, String composedmessage) {
        System.out.println("sending SMS : "+composedmessage);
    }

    @Override
    protected void updateAnalytics(String to) {
        System.out.println("Analytics updated for SMS sent to : "+to);
    }
}

class Main{
    public static void main(String args[]){
        // client code, using the template method to send notifications
        NotificationSender emailSender = new EmailNotificationSender();
        NotificationSender smsSender = new SMSNotificationSender();

        // sending email
        emailSender.sendNotification("john@example.com", "Hello, John!");
        // sending SMS
        smsSender.sendNotification("1234567890", "Hello, John!");

    }
}


/*
Key steos to implement the Template Method pattern:

1. Identify the common steps: Analyze the existing code to identify the common steps that are shared across different implementations (e.g., rate limit checking, validation, logging, etc.).
2. Create an abstract class: Define an abstract class that contains the template method (e.g., sendNotification) which outlines the sequence of steps to be executed. 
            This method should call the common steps and delegate the specific steps to abstract methods.
3. final method in base class: Make the template method final to prevent subclasses from altering the overall algorithm structure, ensuring that the common steps are always executed in the defined order.
4. Define abstract methods: In the abstract class, define abstract methods for the specific steps that need to be implemented by subclasses (e.g., composeMessage and sendMessage).
5. concrete operations(final or concrete methods): Implement the common steps as concrete methods in the abstract class, and provide default implementations if necessary. These methods can be final to prevent overriding, ensuring that the common logic is preserved.
6. hooks(optional methods with default behaviour): If there are steps that may not be necessary for all subclasses, you can define hook methods with default implementations that can be overridden by subclasses if needed (e.g., updateAnalytics).
7. Implement concrete subclasses: Create concrete subclasses that extend the abstract class and provide implementations for the
abstract methods to handle the specific logic for each type of notification (e.g., composing the message and sending it).
8. Use the template method: In the client code, create instances of the concrete subclasses and call the template method to send notifications. This allows you to reuse the common logic while still providing flexibility for different notification types.
*/



/*
how this solves previous issues:

problem: code duplication
solution: the common logic is centralized in the abstract class, eliminating duplication across different notification types.

problem: hardcoded logic and lack of extensibility
solution: new notification types can be added by simply creating new subclasses that implement the specific steps, without modifying existing code.

problem: maintenance overhead
solution: changes to the common logic only need to be made in one place (the abstract class), reducing the risk of bugs and inconsistencies.

*/