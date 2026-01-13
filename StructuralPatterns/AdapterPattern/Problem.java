import java.util.*;

interface PaymentGateway{
    void pay(String orderId, double amount);
}

class PayUGateway implements PaymentGateway{
    public void pay(String orderId, double amount){
        System.out.println("Processing payment of $" + amount + " for order " + orderId + " through PayU Gateway.");
    }
}


// ans existing class with an incompatible interface
class Stripe{
    public void makePayment(String id, double total){
        System.out.println("Processing payment of $" + total + " for order " + id + " through Stripe.");
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

class Problem{
    public static void main(String[] args) {
        CheckoutService checkout1 = new CheckoutService(new PayUGateway());
        checkout1.checkout("ORD123", 250.75);
    }
}

/*
issues:
1. checkoutservice expects any payment provider to implement the PaymentGateway interface.
2. PayUgateway fits this requirement and works correctly.
3. RazorpayApi however uses a different mehtod (makePayment) and does not implement PaymentGateway.
4. due to this mismatch, razorpayapi cannot be used to directly with checkoutservie

this is a case of interface incompatibility, where similar functionalities can't work together becasue of structural differences.
to solve this without modifying the existing code we use the adapter pattern to make razorpayapi compatible with the expected interace

*/