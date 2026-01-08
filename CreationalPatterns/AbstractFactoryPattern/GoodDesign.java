interface PaymentGateway {
    void processPayment(double amount);
}

interface  Invoice {
    void generateInvoice();
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

class GSTInvoice implements Invoice {
    public void generateInvoice() {
        System.out.println("Generating GST Invoice.");
    }
}


class PayPalGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing payment of " + amount + " through PayPal.");
    }
}

class StripeGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing payment of " + amount + " through Stripe.");
    }
}

class USInvoice implements Invoice {
    public void generateInvoice() {
        System.out.println("Generating US Invoice.");
    }
}

interface RegionFactory {
    PaymentGateway createPaymentGateway(String gatewayType);
    Invoice createInvoice();
}


class IndiaFactory implements RegionFactory{
    public PaymentGateway createPaymentGateway(String gatewayType) {
        if(gatewayType.equals("Razorpay")){
            return new Razorpay();
        } else if (gatewayType.equals("PayU")) {
            return new PayUGateway();
        }
        throw new IllegalArgumentException(String.format("Unknown Payment Gateway for India: %s", gatewayType));
    }

    public Invoice createInvoice() {
        return new GSTInvoice();
    }
}


class USFactory implements RegionFactory{
    public PaymentGateway createPaymentGateway(String gatewayType) {
        if(gatewayType.equals("PayPal")){
            return new PayPalGateway();
        } else if (gatewayType.equals("Stripe")) {
            return new StripeGateway();
        }
        throw new IllegalArgumentException(String.format("Unknown Payment Gateway for US: %s", gatewayType));
    }

    public Invoice createInvoice() {
        return new USInvoice();
    }
}


class CheckOutService{
    private PaymentGateway paymentGateway;
    private Invoice invoice;
    
    public CheckOutService(RegionFactory factory, String gatewayType){
        this.paymentGateway = factory.createPaymentGateway(gatewayType);
        this.invoice = factory.createInvoice();
    }

    public void completeOrder(double amount){
        paymentGateway.processPayment(amount);
        invoice.generateInvoice();
    }
}


public class GoodDesign {
    public static void main(String args[]){
        CheckOutService indiaCheckout = new CheckOutService(new IndiaFactory(), "Razorpay");
        indiaCheckout.completeOrder(1500.0);

        System.out.println("-----");

        CheckOutService usCheckout = new CheckOutService(new USFactory(), "PayPal");
        usCheckout.completeOrder(2000.0);
    }
}
