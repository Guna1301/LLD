interface PaymentGateway {
    void processPayment(double amount);
}

class Razorpay implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing payment of " + amount + " through Razorpay.");
    }
}

class PayUGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing payment of " + amount + " through PayU.");
    }
}

interface  Invoice {
    void generateInvoice();
}

class GSTInvoice implements Invoice {
    public void generateInvoice() {
        System.out.println("Generating GST Invoice.");
    }
}

class CheckOutService{
    private String gatewayType;

    public CheckOutService(String gatewayType){
        this.gatewayType = gatewayType;
    }

    public void checkOut(double amount){
        PaymentGateway paymentGateway;

        if(gatewayType.equals("Razorpay")){
            paymentGateway = new Razorpay();
        } else {
            paymentGateway = new PayUGateway();
        }

        paymentGateway.processPayment(amount);

        Invoice invoice = new GSTInvoice();
        invoice.generateInvoice();
    }
}

public class BadDesign {
    public static void main(String args[]){
        CheckOutService checkOutService = new CheckOutService("Razorpay");
        checkOutService.checkOut(1000.0);
    }
}


// Issues with this design:

/*** 
1. Tight Coupling: The CheckOutService class is tightly coupled with specific implementations of PaymentGateway and Invoice. This makes it difficult to add new payment gateways or invoice types without modifying the CheckOutService class.
2. Violation of Open/Closed Principle: The CheckOutService class violates the Open/Closed Principle, which states that classes should be open for extension but closed for modification. Adding a new payment gateway or invoice type requires changing the existing code.
3. Scalability Issues: As the number of payment gateways and invoice types increases, the Check
***/

