import java.util.*;

// represents a simple product in the inventory
class Product{
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void display(String indent) {
        System.out.println(indent + "Product: " + name + ", Price: " + price);
    }
}

class ProductBundle{
    private String bundleName;
    private List<Product> products = new ArrayList<>();

    public ProductBundle(String bundleName) {
        this.bundleName = bundleName;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double getPrice(){
        double total = 0;
        for(Product product : products){
            total += product.getPrice();
        }
        return total;
    }

    public void display(String indent) {
        System.out.println(indent + "Bundle: " + bundleName + ", Total Price: " + getPrice());
        for(Product product : products){
            product.display(indent + "  ");
        }
    }
}

public class Problem {
    public static void main(String args[]){
        //individual products
        Product book = new Product("Book", 500);
        Product headPhones = new Product("HeadPhones", 1500);
        Product charger = new Product("Charger", 800);
        Product pen = new Product("Pen", 20);
        Product notebook = new Product("Notebook", 60);


        // bundle: Iphone combo
        ProductBundle iphoneCombo = new ProductBundle("Iphone Combo");
        iphoneCombo.addProduct(charger);
        iphoneCombo.addProduct(headPhones);

        // bundle: Stationery combo
        ProductBundle stationeryCombo = new ProductBundle("Stationery Combo");
        stationeryCombo.addProduct(pen);
        stationeryCombo.addProduct(notebook);

        List<Object> cart = new ArrayList<>();
        cart.add(book);
        cart.add(iphoneCombo);
        cart.add(stationeryCombo);

        // display cart details
        double total = 0;
        System.out.println("Cart Details:\n");
        for(Object item : cart){
            if(item instanceof Product){
                Product prod = (Product) item;
                prod.display("  ");
                total += prod.getPrice();
            } else if(item instanceof ProductBundle){
                ProductBundle bundle = (ProductBundle) item;
                bundle.display("  ");
                total += bundle.getPrice();
            }
        }
        System.out.println("\nTotal Cart Price: " + total);
    }
}

/*
working of this code:
- Product class represents individual items with name and price.
- ProductBundle class represents a collection of products, allowing addition of products and calculating total price.
- both classes have methods to display and return their prices.
- in main(), individual products and bundles are created, added to a cart, and their details along with total price are displayed.
- the cart is List<object> this holds both products and bundles
- during checkout, the code checks each item's type using instanceof to call appropriate methods for displaying and calculating prices.
- based on the type, it casts the object and calls its respective methods.
*/

/*
proublem in above code:

in above example, the code lacks the structe to treat individual and group items uniformsly,
i.e., in the current implementation, individual products(Product) and product bundles(ProductBundle) are handled differently.
this means we cannot write code that treats both unifrmly and the logic always has to check which type we are working with

other than these, there are some other probems
- instance of is used repeatldy which breaks polymorphism
- cart uses List<Object> which is unsafe and violates abstraction
- ProductBundle cannot contain other ProductBundles, limiting its flexibility
- display and price logic are duplicated across classes

*/