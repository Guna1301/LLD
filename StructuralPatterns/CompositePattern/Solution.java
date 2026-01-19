import java.util.*;

interface CartItem {
    double getPrice();
    void display(String indent);
}

// product class implementing CartItem
class Product implements CartItem {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "Product: " + name + ", Price: " + price);
    }
}

class ProductBundle implements CartItem {
    private String bundleName;
    private List<CartItem> items = new ArrayList<>();

    public ProductBundle(String bundleName) {
        this.bundleName = bundleName;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    @Override
    public double getPrice(){
        double total = 0;
        for(CartItem item : items){
            total += item.getPrice();
        }
        return total;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "Bundle: " + bundleName + ", Total Price: " + getPrice());
        for(CartItem item : items){
            item.display(indent + "  ");
        }
    }
}

public class Solution {
    public static void main(String[] args) {
        //individual products
        Product book = new Product("Book", 500);
        Product headPhones = new Product("HeadPhones", 1500);
        Product charger = new Product("Charger", 800);
        Product pen = new Product("Pen", 20);
        Product notebook = new Product("Notebook", 60);

        // bundle: Iphone combo
        ProductBundle iphoneCombo = new ProductBundle("Iphone Combo");
        iphoneCombo.addItem(charger);
        iphoneCombo.addItem(headPhones);

        // bundle: Stationery combo
        ProductBundle stationeryCombo = new ProductBundle("Stationery Combo");
        stationeryCombo.addItem(pen);
        stationeryCombo.addItem(notebook);

        List<CartItem> cart = new ArrayList<>();
        cart.add(book);
        cart.add(iphoneCombo);
        cart.add(stationeryCombo);

        // Display cart items and total price
        double totalPrice = 0;
        System.out.println("Shopping Cart: \n");
        for(CartItem item : cart){
            item.display("  ");
            totalPrice += item.getPrice();
        }
        System.out.println("\nTotal Cart Price: " + totalPrice);
    }
}

/*
working of this code:
- CartItem interface defines common methods for both individual products and bundles.
- Product class represents individual items with name and price.
- ProductBundle class represents a collection of CartItems (both Products and other Bundles).
- In the main method, we create individual products and bundles, add products to bundles, and then add both products and bundles to the shopping cart.
the cart now holds a list of CartItem objects, allowing uniform treatment of both individual products and bundles.
- the display and price calculation logic is simplified, as we no longer need to check types
*/