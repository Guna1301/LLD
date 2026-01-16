package StructuralPatterns.DecoratorPattern;

class PlainPizza{}

class CheesePizza extends PlainPizza{}
class OlivePizza extends PlainPizza{}
class StuffedPizza extends PlainPizza{}

class CheeseStuffedPizza extends CheesePizza{}
class CheeseOlivePizza extends CheesePizza{}
class CheeseOliveStuffedPizza extends CheeseOlivePizza{}


public class Problem {
    public static void main(String[] args) {
        // Base pizza
        PlainPizza plainPizza = new PlainPizza();

        // pizzas with individual toppings
        CheesePizza cheesePizza = new CheesePizza();
        OlivePizza olivePizza = new OlivePizza();
        StuffedPizza stuffedPizza = new StuffedPizza();

        // combinations fo roppings requre seperate classes
        CheeseStuffedPizza cheeseStuffedPizza = new CheeseStuffedPizza();
        CheeseOlivePizza cheeseOlivePizza = new CheeseOlivePizza();

        // further combinations increase complexity exponentially
        CheeseOliveStuffedPizza cheeseOliveStuffedPizza = new CheeseOliveStuffedPizza();

    }
}

/*
here for every combination we would need to create a new subclass as shown in the code above
This approach is not scalable and violates the Open/Closed Principle.
A better approach would be to use the Decorator Pattern, where we can dynamically add toppings to a base pizza without creating a new subclass for each combination.
*/
