import java.util.*;

// builder pattern implementation

class BurgerMeal{
    // required components
    private final String bun;
    private final String patty;

    // optional components
    private final  boolean hasCheese;
    private final List<String> toppings;
    private final String sides;
    private final String drink;

    // private constructor to force use of builder
    private BurgerMeal(BurgerBuilder builder){
        this.bun = builder.bun;
        this.patty = builder.patty;
        this.hasCheese = builder.hasCheese;
        this.toppings = builder.toppings;
        this.sides = builder.sides;
        this.drink = builder.drink;
    }

    public static class BurgerBuilder{
        // required
        private final String bun;
        private final String patty;

        // optional
        private boolean hasCheese;
        private List<String> toppings;
        private String sides;
        private String drink;

        // builder construtor for required fields
        public BurgerBuilder(String bun, String patty){
            this.bun = bun;
            this.patty = patty;
        }

        public BurgerBuilder addCheese(boolean hasCheese){
            this.hasCheese = hasCheese;
            return this;
        }

        public BurgerBuilder addToppings(List<String> toppings){
            this.toppings = toppings;
            return this;
        }

        public BurgerBuilder addSides(String sides){
            this.sides = sides;
            return this;
        }

        public BurgerBuilder addDrink(String drink){
            this.drink = drink;
            return this;
        }

        public BurgerMeal build(){
            return new BurgerMeal(this);    
        }
    }
}

public class Solution {
    public static void main(String[] args) {

        // creating burger with only required feils
        BurgerMeal plainBurger = new BurgerMeal.BurgerBuilder("wheat","veg").build();

        // burger with cheese only
        BurgerMeal cheeseBurger = new BurgerMeal.BurgerBuilder("wheat","chicken")
                                        .addCheese(true)
                                        .build();
        
        // full featured burger
        BurgerMeal fullBurger = new BurgerMeal.BurgerBuilder("white","beef")
                                        .addCheese(true)
                                        .addToppings(Arrays.asList("Lettuce","Tomato","Onion"))
                                        .addSides("Fries")
                                        .addDrink("Coke")
                                        .build();

        
    }
}

/*
// why private constructor?
    - Prevents creating BurgerMeal directly using new
    -Forces usage of Builder only
// nested static class?
    - Groups builder with BurgerMeal
    - No need for outer class instance to create builder

// method chaining?
    - Each setter returns builder instance
    - Allows chaining multiple setter calls in single statement

// immutability?
    - BurgerMeal fields are final
    - State cannot be changed after construction
// fluent api style?
    - Builder methods read like sentences
    - Improves code readability

// selective configuration?
    - Only set desired optional fields
    - No need to pass nulls for unused fields

// final step build()?
    - once all desired feilds are set, calling .build() finalizes 
    the object contruction and returns the burgermeal instance
*/
