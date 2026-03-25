package BehaviouralPatterns.ChainOfResponsibilityPattern;

class SupportService{
    public void handleRequest(String request){
        if(request.equals("Technical Issue")){
            System.out.println("Handling technical issue...");
        } else if(request.equals("Billing Issue")){
            System.out.println("Handling billing issue...");
        } else if(request.equals("General Inquiry")){
            System.out.println("Handling general inquiry...");
        }else {
            System.out.println("Request not handled.");
        }
    }
}

public class Problem {
    public static void main(String[] args) {

        // In this implementation, the SupportService class is responsible for handling all types of requests.
        SupportService supportService = new SupportService();
        
        supportService.handleRequest("Technical Issue");
        supportService.handleRequest("Billing Issue");
        supportService.handleRequest("General Inquiry");
        supportService.handleRequest("Unknown Issue");
    }
}

/*
problem with this implementation:

1. violation of ocp: every time we need to add a new type of request, we have to modify the SupportService class, which violates the Open/Closed Principle (OCP).
2. monolithic code: the SupportService class is responsible for handling all types of requests, which can lead to a large and complex class that is difficult to maintain and extend.
3. lack of flexibility: if we want to change the way a specific type of request is handled, we have to modify the SupportService class, which can affect other types of requests and lead to unintended consequences.

*/