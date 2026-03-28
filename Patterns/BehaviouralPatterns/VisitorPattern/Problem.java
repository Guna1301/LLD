package BehaviouralPatterns.VisitorPattern;

import java.util.*;

// class representing a physical product
class PhysicalProduct{
    void printInvoice(){
        System.out.println("Printing invoice for physical product...");
    }

    double calculateShippingCost(){
        System.out.println("Calculating shipping cost for physical product...");
        return 10.0; // Example shipping cost
    }
}

// class representing a digital product
class DigitalProduct{
    void printInvoice(){
        System.out.println("Printing invoice for digital product...");
    }

    // no shipping cost for digital products
}

// class representing a gift card
class GiftCard{
    void printInvoice(){
        System.out.println("Printing invoice for gift card...");
    }

    double calculateShippingCost(){
        System.out.println("Calculating shipping cost for gift card...");
        return 5.0; // Example shipping cost
    }
}

public class Problem {
    public static void main(String[] args) {
        // create instances of different product types
        List<Object>cart = Arrays.asList(new PhysicalProduct(), new DigitalProduct(), new GiftCard());

        // process each product in the cart
        for(Object product : cart){
            if(product instanceof PhysicalProduct){
                PhysicalProduct physicalProduct = (PhysicalProduct) product;
                physicalProduct.printInvoice();
                System.out.println("Shipping cost: " + physicalProduct.calculateShippingCost());
            } else if(product instanceof DigitalProduct){
                DigitalProduct digitalProduct = (DigitalProduct) product;
                digitalProduct.printInvoice();
                // no shipping cost for digital products
            } else if(product instanceof GiftCard){
                GiftCard giftCard = (GiftCard) product;
                giftCard.printInvoice();
                System.out.println("Shipping cost: " + giftCard.calculateShippingCost());
            }
        }
    } 
}

// In this implementation, we have three different product types: PhysicalProduct, DigitalProduct, and GiftCard. Each product type has its own methods for printing invoices and calculating shipping costs.
// The main method processes a list of products in the cart, checking the type of each product and calling the appropriate methods.
// This approach can lead to code that is difficult to maintain and extend, as adding new product types would require modifying the existing code to include new type checks and method calls, which violates the Open/Closed Principle (OCP) and can lead to a monolithic code structure.

/*
issues with this implementation:
1. violates srp: each product class is responsible for multiple functionalities (printing invoice and calculating shipping cost), which can lead to code that is difficult to maintain and extend.
2. violates ocp: adding new product types would require modifying existing code to include new type checks and method calls, which can lead to bugs and maintenance issues.
3. product type checking in client code: the client code needs to check the type of each product and call the appropriate methods, which can lead to code that is difficult to read and maintain.
4. lack of flexibility: if we want to change the way specific types of products are processed (e.g., changing the shipping cost calculation for physical products), we would need to modify the existing code, which can lead to bugs and maintenance issues.
5. tight coupling: the client code is tightly coupled to the specific product types, which can make it difficult to change or extend the product types without affecting the client code.

*/
