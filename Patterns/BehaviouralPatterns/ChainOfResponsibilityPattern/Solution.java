package BehaviouralPatterns.ChainOfResponsibilityPattern;

// In the Chain of Responsibility pattern, we create a chain of handler objects, where each handler is responsible for processing a specific type of request. 
// If a handler cannot process the request, it passes it to the next handler in the chain until it is handled or reaches the end of the chain.
abstract class SupportHandler{
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String request);
}


class GeneralInquiryHandler extends SupportHandler{
    @Override
    public void handleRequest(String request) {
        if(request.equals("General Inquiry")){
            System.out.println("Handling general inquiry...");
        } else if(nextHandler != null){
            nextHandler.handleRequest(request);
        } else {
            System.out.println("Request not handled.");
        }
    }
}

class TechnicalIssueHandler extends SupportHandler{
    @Override
    public void handleRequest(String request) {
        if(request.equals("Technical Issue")){
            System.out.println("Handling technical issue...");
        } else if(nextHandler != null){
            nextHandler.handleRequest(request);
        } else {
            System.out.println("Request not handled.");
        }
    }
}

class BillingIssueHandler extends SupportHandler{
    @Override
    public void handleRequest(String request) {
        if(request.equals("Billing Issue")){
            System.out.println("Handling billing issue...");
        } else if(nextHandler != null){
            nextHandler.handleRequest(request);
        } else {
            System.out.println("Request not handled.");
        }
    }
}



public class Solution {
    public static void main(String[] args) {
        // In this implementation, we have created separate handler classes for each type of request, and we have set up a chain of handlers to process the requests. 
        // This allows us to add new types of requests without modifying existing code, and it promotes separation of concerns and flexibility in handling requests.

        SupportHandler generalInquiryHandler = new GeneralInquiryHandler();
        SupportHandler technicalIssueHandler = new TechnicalIssueHandler();
        SupportHandler billingIssueHandler = new BillingIssueHandler();

        generalInquiryHandler.setNextHandler(technicalIssueHandler);
        technicalIssueHandler.setNextHandler(billingIssueHandler);

        generalInquiryHandler.handleRequest("Technical Issue");
        generalInquiryHandler.handleRequest("Billing Issue");
        generalInquiryHandler.handleRequest("General Inquiry");
        generalInquiryHandler.handleRequest("Unknown Issue");
    }
}


/*
how this solution addresses the problem:

problem: violation of ocp
solution: by using the Chain of Responsibility pattern, we can add new types of requests by creating new handler classes that extend the SupportHandler class. 
          This allows us to adhere to the Open/Closed Principle (OCP) since we can add new functionality without modifying existing code.

problem: monolithic code
solution: by separating the handling of different types of requests into separate handler classes, we can avoid creating a large and complex SupportService class. 
          Each handler class is responsible for handling a specific type of request, which promotes separation of concerns and makes the code easier to maintain and extend.

problem: lack of flexibility
solution: by using a chain of handlers, we can easily change the way specific types of requests are handled by modifying or adding new handler classes without affecting other types of requests. 
          This promotes flexibility and allows for more modular code.

*/