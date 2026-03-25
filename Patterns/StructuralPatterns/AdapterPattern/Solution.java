interface PaymentGateway{
    void pay(String orderId, double amount);
}

class PayUGateway implements PaymentGateway{
    public void pay(String orderId, double amount){
        System.out.println("Processing payment of $" + amount + " for order " + orderId + " through PayU Gateway.");
    }
}

// existing class with an incompatible interface
class StripeAPI{
    public void makePayment(String id, double total){
        System.out.println("Processing payment of $" + total + " for order " + id + " through Stripe.");
    }
}

// Adapter class to make Stripe compatible with PaymentGateway
class StripeAdapter implements PaymentGateway{
    private StripeAPI stripeAPI;

    public StripeAdapter(){
        this.stripeAPI = new StripeAPI();
    }

    public void pay(String orderId, double amount){
        stripeAPI.makePayment(orderId, amount);
    }
}


class CheckoutService{
    private PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

    public void checkout(String orderId, double amount){
        paymentGateway.pay(orderId, amount);
    }
}

public class Solution{
    public static void main(String[] args) {
        CheckoutService checkout1 = new CheckoutService(new PayUGateway());
        checkout1.checkout("ORD123", 250.75);

        // Using Stripe through the Adapter
        CheckoutService checkout2 = new CheckoutService(new StripeAdapter());
        checkout2.checkout("ORD456", 150.50);
    }
}