package examples.BasicCommunicationGateway;

enum NotificationChannel {
    EMAIL, SMS, PUSH
}

enum NotificationPriority {
    LOW, MEDIUM, HIGH
}

class NotificationRequest{
    NotificationChannel channel;
    String recipient;
    String message;
    NotificationPriority priority;
}


// strategy pattern
interface NotificationStrategy{
    void send(NotificationRequest request);
    void validate(NotificationRequest request);
}


// template pattern 
abstract class AbstractNotificationStrategy implements NotificationStrategy{
    public final void process(NotificationRequest request){
        validateCommon(request);
        validate(request);
        format(request);
        send(request);
    }
    private void validateCommon(NotificationRequest request){
        if(request.channel == null) {
            throw new IllegalArgumentException("Channel cannot be null");
        }

        if(request.recipient == null || request.recipient.isEmpty()) {
            throw new IllegalArgumentException("Recipient cannot be null or empty");
        }

        if(request.message == null || request.message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
    }
    protected void format(NotificationRequest request){
        request.message = request.message.trim();
    }
    public abstract void validate(NotificationRequest request);
    public abstract void send(NotificationRequest request);
}


class EmailStrategy extends AbstractNotificationStrategy{
    @Override
    public void validate(NotificationRequest request) {
        if(!request.recipient.contains("@")) {
            throw new IllegalArgumentException("Invalid email address: " + request.recipient);
        }
    }

    @Override
    public void format(NotificationRequest request) {
        super.format(request);
        request.message = "Email: " + request.message;
    }

    @Override
    public void send(NotificationRequest request) {
        System.out.println("Sending email to " + request.recipient + " with message: " + request.message);
    }
}

class SmsStrategy extends AbstractNotificationStrategy{
    @Override
    public void validate(NotificationRequest request) {
        if(!request.recipient.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid phone number: " + request.recipient);
        }
    }

    @Override
    public void format(NotificationRequest request) {
        super.format(request);
        request.message = "SMS: " + request.message;
    }

    @Override
    public void send(NotificationRequest request) {
        System.out.println("Sending SMS to " + request.recipient + " with message: " + request.message);
    }
}

class PushStrategy extends AbstractNotificationStrategy{
    @Override
    public void validate(NotificationRequest request) {
        if(request.priority == null) {
            throw new IllegalArgumentException("Priority cannot be null for push notifications");
        }
    }

    @Override
    public void format(NotificationRequest request) {
        super.format(request);
        request.message = "Push: " + request.message + " (Priority: " + request.priority + ")";
    }

    @Override
    public void send(NotificationRequest request) {
        System.out.println("Sending push notification to " + request.recipient + " with message: " + request.message);
    }
}


// factory pattern
class NotificationFactory{
    public static AbstractNotificationStrategy getStrategy(NotificationChannel channel) {
        switch (channel) {
            case EMAIL:
                return new EmailStrategy();
            case SMS:
                return new SmsStrategy();
            case PUSH:
                return new PushStrategy();
            default:
                throw new IllegalArgumentException("Unsupported notification channel: " + channel);
        }
    }
}

class NotificationService{
    public void processNotification(NotificationRequest request){
        if(request == null) {
            throw new IllegalArgumentException("Notification request cannot be null");
        }
        if(request.channel == null) {
            throw new IllegalArgumentException("Notification channel cannot be null");
        }
        AbstractNotificationStrategy strategy = NotificationFactory.getStrategy(request.channel);
        try {
            strategy.process(request);
        } catch (Exception e) {
            System.err.println("Failed to process notification: " + e.getMessage());
        }
        
    }
}


// client code
public class Solution1 {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        NotificationRequest emailRequest = new NotificationRequest();
        emailRequest.channel = NotificationChannel.EMAIL;
        emailRequest.recipient = "user@example.com";
        emailRequest.message = "Hello, this is an email notification.";

        service.processNotification(emailRequest);

        NotificationRequest smsRequest = new NotificationRequest();
        smsRequest.channel = NotificationChannel.SMS;
        smsRequest.recipient = "1234567890";
        smsRequest.message = "Hello, this is an SMS notification.";

        service.processNotification(smsRequest);

        NotificationRequest pushRequest = new NotificationRequest();
        pushRequest.channel = NotificationChannel.PUSH;
        pushRequest.recipient = "userDeviceToken";
        pushRequest.message = "Hello, this is a push notification.";
        pushRequest.priority = NotificationPriority.HIGH;

        service.processNotification(pushRequest);

        // Invalid email
        NotificationRequest invalidEmailRequest = new NotificationRequest();
        invalidEmailRequest.channel = NotificationChannel.EMAIL;
        invalidEmailRequest.recipient = "invalid-email";
        invalidEmailRequest.message = "Hello, this is an invalid email notification.";

        service.processNotification(invalidEmailRequest);
    }
}