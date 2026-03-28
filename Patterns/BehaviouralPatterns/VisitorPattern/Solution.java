package BehaviouralPatterns.VisitorPattern;

import java.util.*;

// element interface
interface Item{
    void accept(ItemVisitor visitor);
}

// concrete element classes
class PhysicalProduct implements Item{
    String name;
    double weight;
    public PhysicalProduct(String name, double weight){
        this.name = name;
        this.weight = weight;
    }
    @Override
    public void accept(ItemVisitor visitor){
        visitor.visit(this);
    }
}


// class representing a digital product
class DigitalProduct implements Item{
    String name;
    double fileSize;
    public DigitalProduct(String name, double fileSize){
        this.name = name;
        this.fileSize = fileSize;
    }
    @Override
    public void accept(ItemVisitor visitor){
        visitor.visit(this);
    }
}

// class representing a gift card   
class GiftCard implements Item{
    String code;
    double value;
    public GiftCard(String code, double value){
        this.code = code;
        this.value = value;
    }
    @Override
    public void accept(ItemVisitor visitor){
        visitor.visit(this);
    }
}


//visitor interface
interface ItemVisitor{
    void visit(PhysicalProduct product);
    void visit(DigitalProduct product);
    void visit(GiftCard giftCard);
}


// concrete visitor class
class InvoiceVisitor implements ItemVisitor{
    @Override
    public void visit(PhysicalProduct product){
        System.out.println("Printing invoice for physical product: " + product.name);
    }

    @Override
    public void visit(DigitalProduct product){
        System.out.println("Printing invoice for digital product: " + product.name);
    }

    @Override
    public void visit(GiftCard giftCard){
        System.out.println("Printing invoice for gift card: " + giftCard.code);
    }
}

class ShippingCostVisitor implements ItemVisitor{
    @Override
    public void visit(PhysicalProduct product){
        System.out.println("Calculating shipping cost for physical product: " + product.name);
        System.out.println("Shipping cost: " + calculateShippingCost(product));
    }

    @Override
    public void visit(DigitalProduct product){
        System.out.println("No shipping cost for digital product: " + product.name);
    }

    @Override
    public void visit(GiftCard giftCard){
        System.out.println("Calculating shipping cost for gift card: " + giftCard.code);
        System.out.println("Shipping cost: " + calculateShippingCost(giftCard));
    }
    
    private double calculateShippingCost(PhysicalProduct product){
        return 10.0;
    }
    private double calculateShippingCost(GiftCard giftCard){
        return 5.0; 
    }
}


public class Solution {
    public static void main(String[] args) {
        // create instances of different product types
        List<Item> cart = Arrays.asList(
            new PhysicalProduct("Laptop", 2.5),
            new DigitalProduct("E-book", 0.5),
            new GiftCard("GIFT123", 50.0)
        );

        // create visitors
        ItemVisitor invoiceVisitor = new InvoiceVisitor();
        ItemVisitor shippingCostVisitor = new ShippingCostVisitor();

        // process each product in the cart with both visitors
        for(Item item : cart){
            item.accept(invoiceVisitor);
            item.accept(shippingCostVisitor);
            System.out.println();
        }
    }
}

/*
how this solves previous issues:

problem: doen't follow srp
solution: we have separate visitor classes for different operations (InvoiceVisitor and ShippingCostVisitor), so each class has a single responsibility.

problem: violates open/closed principle
solution: we can add new operations (new visitors) without modifying existing item classes, adhering to the open/closed principle.

problem: type checking and casting
solution: the accept method and visitor pattern eliminate the need for type checking and casting, as the correct visit method is called based on the actual type of the item.

problem: lack of flexibility
solution: we can easily add new operations by creating new visitor classes without changing the item classes, providing greater flexibility in extending functionality.

problem: tight coupling
solution: the client code is decoupled from the specific item types, as it interacts with the items through the Item interface and uses visitors to perform operations, reducing tight coupling and improving maintainability.

*/


/*
Double dispatch

- double dispatch is a technique used in object-oriented programming to resolve method calls based on the runtime types of two objects involved in the call. It allows for more flexible and dynamic behavior by enabling method selection based on the types of both the caller and the argument.

- it is commonly used in visitoe pattern to enable diffent actions depending in both the type of the visitor and the type of element being visited. here how it works:

    1. first dispatch: when an element (e.g., PhysicalProduct) accepts a visitor (e.g., InvoiceVisitor), it calls the accept method on the element, passing the visitor as an argument. 
        This is the first dispatch, where the method call is resolved based on the runtime type of the element.
    2. second dispatch: the visit() method in the visitor class is then responsible for determining the type of element being visited and executing the appropriate logic.
         This is the second dispatch, where the method call is resolved based on the runtime type of the visitor.

double dispatch allows for more flexible and extensible code, as new operations can be added by simply creating new visitor classes without modifying the existing element classes. 
It also promotes separation of concerns, as the logic for different operations is encapsulated in separate visitor classes, adhering to the single responsibility principle.

*/