class EmailNotification{
    public void send(String to, String message){
        System.out.println("checking ratelimit for : "+to);
        System.out.println("validating email address : "+to);
        String formated = message.trim().toLowerCase();
        System.out.println("logging before send: "+formated+ " to "+to);

        // compose email and send
        String composedEmail = "Dear "+to+",\n"+formated;

        // send email
        System.out.println("sending email : "+composedEmail);

        //analytics
        System.out.println("Analytics updated for email sent to : "+to);
    }
}

class SMSNotification{
    public void send(String to, String message){
        System.out.println("checking ratelimit for : "+to);
        System.out.println("validating phone number : "+to);
        String formated = message.trim().toLowerCase();
        System.out.println("logging before send: "+formated+ " to "+to);

        // compose email and send
        String composedSMS = "Dear "+to+",\n"+formated;

        // send email
        System.out.println("sending SMS : "+composedSMS);

        //analytics
        System.out.println("Analytics updated for SMS sent to : "+to);
    }
}

class Problem{
    public static void main(String[] args) {
        // client code, objects are created and used directly
        EmailNotification emailNotification = new EmailNotification();
        SMSNotification smsNotification = new SMSNotification();

        // sending email
        emailNotification.send("john@example.com", "Hello, this is an email message.");

        // sending SMS
        smsNotification.send("+1234567890", "Hello, this is an SMS message.");

    }
}

/*
problems with this code:

- code duplication: both EmailNotification and SMSNotification have similar code for checking rate limits, validating addresses, logging, and analytics. 
                    This violates the DRY (Don't Repeat Yourself) principle.
- hardcoded logic: the logic for sending notifications is tightly coupled with the specific notification types, making it difficult to add new notification types in the future without modifying existing code.
- lack of extensibility: if we want to add new notification types (e.g., push notifications), we would need to create new classes and duplicate the common logic, leading to maintenance issues.
- maintenance overhead: any changes to the common logic (e.g., changing the way rate limits are checked or how analytics are updated) would require changes in multiple places, increasing the risk of bugs and inconsistencies.

*/