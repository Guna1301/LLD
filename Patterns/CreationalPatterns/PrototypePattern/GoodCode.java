package CreationalPatterns.PrototypePattern;
import java.util.*;

interface EmailTemplate extends Cloneable {
    void setContent(String content);
    void send(String to);
    EmailTemplate clone();
}

class WelcomeEmail implements EmailTemplate{
    private String subject;
    private String content;

    public WelcomeEmail(){
        this.subject = "Welcome to Our Service!";
        this.content = "Dear User, Welcome to our service. We are glad to have you.";
    }

    @Override
    public WelcomeEmail clone(){
        try {
            return (WelcomeEmail)super.clone();
        } catch (Exception e) {
            throw new RuntimeException("Cloning not supported");
        }
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

class EmailTemplateRegistry{
    private static final Map<String, EmailTemplate> templates = new HashMap<>();

    static{
        templates.put("welcome", new WelcomeEmail());
        //templates.put("promotion", new PromotionEmail());
    }

    public static EmailTemplate getTemplate(String type){
        EmailTemplate template = templates.get(type);
        return template != null ? template.clone() : null;
    }
}

public class GoodCode {
    public static void main(String[] args) {
        EmailTemplate email1 = EmailTemplateRegistry.getTemplate("welcome");
        email1.setContent("hi dumb, welcome to this platfrom!");
        email1.send("dum@gmail.com");

        EmailTemplate email2 = EmailTemplateRegistry.getTemplate("welcome");
        email2.setContent("Dear User, Welcome aboard! We hope you enjoy our service.");
        email2.send("dum2@gmail.com");
    }
}

/*
    improvements in the above code:
1. implemets clone(): alows object copying insted of creating from scratch
2. EmailTemplateRegistry: centralizes template management and cloning
3. reduces code duplication: cloning existing objects instead of re-initializing
4. improves perfromace: cloning is often more efficient than creating new instances
*/