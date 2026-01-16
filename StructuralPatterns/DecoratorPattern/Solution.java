package StructuralPatterns.DecoratorPattern;
import java.util.*;

interface Pizza{
    String getDescription();
    double getCost();
}

// concrete components: Base pizza
class PlainPizza implements Pizza{
    public String getDescription(){
        return "Plain Pizza";
    }
    public double getCost(){
        return 150.0;
    }
}

class MargheritaPizza implements Pizza{
    public String getDescription(){
        return "Margherita Pizza";
    }
    public double getCost(){
        return 200.0;
    }
}

// abstract decorators
// implemts pizza and holds a refrence to a pizza object
abstract class PizzaDecorator implements Pizza{
    protected Pizza pizza;
    public PizzaDecorator(Pizza pizza){
        this.pizza = pizza;
    }
}

// concrete decorators
class ExtraCheese extends PizzaDecorator{
    public ExtraCheese(Pizza pizza){
        super(pizza);
    }
    public String getDescription(){
        return pizza.getDescription() + ", Extra Cheese";
    }
    public double getCost(){
        return pizza.getCost() + 50.0;
    }
}

class Olives extends PizzaDecorator{
    public Olives(Pizza pizza){
        super(pizza);
    }
    public String getDescription(){
        return pizza.getDescription() + ", Olives";
    }
    public double getCost(){
        return pizza.getCost() + 30.0;
    }
}

class StuffedCrust extends PizzaDecorator{
    public StuffedCrust(Pizza pizza){
        super(pizza);
    }
    public String getDescription(){
        return pizza.getDescription() + ", Stuffed Crust";
    }
    public double getCost(){
        return pizza.getCost() + 70.0;
    }
}



public class Solution {
    public static void main(String[] args) {
        Pizza pizza = new MargheritaPizza();

        // Adding extra cheese
        pizza = new ExtraCheese(pizza);

        // Adding olives
        pizza = new Olives(pizza);

        // Adding stuffed crust
        pizza = new StuffedCrust(pizza);

        System.out.println("Description: " + pizza.getDescription());
        System.out.println("Total Cost: " + pizza.getCost());
    }
}


/*
above code:
difines a pizza interface that all pizza must implement
implemets two concrete plainpizza and margherita pizza as base pizzas
defines an abstract decorator class that implements the pizza interface and holds a reference to a pizza object
implements concrete decorators for extra cheese, olives, and stuffed crust that extend the abstract decorator class
The main method demonstrates how to create a margherita pizza and dynamically add toppings using decorators, printing the final description and cost.
package StructuralPatterns.DecoratorPattern;

how decorator pattern solve the issue here:
- avoid class explosion: instead of creating a new subclass for every possible combination of toppings, we create a set of decorators that can be combined dynamically.
- flexible and scalable: new toppings can be added as new decorators without modifying existing code.
- follows open/closed principle: classes are open for extension (by adding new decorators) but closed for modification (existing classes remain unchanged).
- cleaner code architecture
- promotes reusability: each topping is implemented as a separate class that can be reused across different pizza types.

*/