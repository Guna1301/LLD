import java.util.*;

class BurgerMeal{
    // mandatory fields
    private String bun;
    private String patty;

    // optional fields
    private boolean cheese;
    private String sides;
    private List<String> toppings;

    public BurgerMeal(String bun, String patty, String sides, List<String> toppings, boolean cheese) {
        this.bun = bun;
        this.patty = patty;
        this.sides = sides;
        this.toppings = toppings;
        this.cheese = cheese;
    }

}

public class Problem {
    public static void main(String args[]){
        BurgerMeal meal = new BurgerMeal("whaet","veg",null,null,false);
    }
}

/*
    Issues in code
1. hard to read and maintain
2. unnecesarry null values;
3. Risk of null pointer exceptions
4. Too many contructor overloads
5. tight coupling between parameters and contruction


 */
