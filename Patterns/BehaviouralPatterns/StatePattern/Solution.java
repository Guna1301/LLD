

package BehaviouralPatterns.StatePattern;

class OrderContext{
    private OrderState currState;

    // constructor to initialize the order state to ORDER_PLACED
    public OrderContext(){
        this.currState = new OrderPlacedState();
    }

    // method to set a new state for the order
    public void setState(OrderState state){
        this.currState = state;
    }

    public void next(){
        currState.next(this);
    }

    // method to cancel the order
    public void cancelOrder(){
        currState.cancel(this);
    }

    public String getState(){
        return currState.getState();
    }
}

interface OrderState{
    void cancel(OrderContext context);
    void next(OrderContext context);
    String getState();
}


class OrderPlacedState implements OrderState{
    public void next(OrderContext context){
        context.setState(new PreparingState());
        System.out.println("Order state changed to: PREPARING");
    }

    public void cancel(OrderContext context){
        context.setState(new CancelledState());
        System.out.println("Order cancelled successfully.");
    }

    public String getState(){
        return "ORDER_PLACED";
    }

}

class PreparingState implements OrderState{
    public void next(OrderContext context){
        context.setState(new OutForDeliveryState());
        System.out.println("Order state changed to: OUT_FOR_DELIVERY");
    }

    public void cancel(OrderContext context){
        context.setState(new CancelledState());
        System.out.println("Order cancelled successfully.");
    }

    public String getState(){
        return "PREPARING";
    }
}

class OutForDeliveryState implements OrderState{
    public void next(OrderContext context){
        context.setState(new DeliveredState());
        System.out.println("Order state changed to: DELIVERED");
    }

    public void cancel(OrderContext context){
        System.out.println("Order cannot be cancelled in the current state: OUT_FOR_DELIVERY");
    }

    public String getState(){
        return "OUT_FOR_DELIVERY";
    }
}

class DeliveredState implements OrderState{
    public void next(OrderContext context){
        System.out.println("Order is already delivered. No further state changes allowed.");
    }

    public void cancel(OrderContext context){
        System.out.println("Order cannot be cancelled in the current state: DELIVERED");
    }

    public String getState(){
        return "DELIVERED";
    }
}

class CancelledState implements OrderState{
    public void next(OrderContext context){
        System.out.println("Order is cancelled. No further state changes allowed.");
    }

    public void cancel(OrderContext context){
        System.out.println("Order is already cancelled.");
    }

    public String getState(){
        return "CANCELLED";
    }
}


public class Solution {
    public static void main(String args[]){
        OrderContext order = new OrderContext();

        // Display initial state
        System.out.println("Current Order State: " + order.getState());

        // Move to PREPARING
        order.next(); // order state changed to: PREPARING
        order.next(); // order state changed to: OUT_FOR_DELIVERY
        order.cancelOrder(); // order cannot be cancelled in the current state: OUT_FOR_DELIVERY
        order.next(); // order state changed to: DELIVERED
        order.cancelOrder(); // order cannot be cancelled in the current state: DELIVERED

        // Display final state
        System.out.println("Final Order State: " + order.getState());
    }
}

/*
how this solves previous issues:

problem: state change management
solution: By using the State pattern, we encapsulate the state-specific behavior within separate classes that implement a common interface. 
        Each state class is responsible for managing its own transitions and actions, which eliminates the need for complex conditional logic in the main Order class. This makes the code more modular, easier to maintain, and extendable for future states or changes in behavior.

problem: lack of encapsulation
solution: The State pattern promotes encapsulation by defining a clear interface for state behavior and allowing each state to manage its own logic. 
        This means that the OrderContext class does not need to know the details of how each state operates, and the state classes can be modified or extended without affecting the overall structure of the OrderContext. 
        This separation of concerns enhances code readability and maintainability.

problem: code duplication
solution: The State pattern eliminates code duplication by centralizing state-specific behavior within the respective state classes. 
        Instead of having multiple conditional statements scattered throughout the Order class to handle different states, each state class contains its own implementation of the next and cancel methods. 
        This reduces redundancy and makes the code cleaner and easier to manage.

problem: scalability
solution: The State pattern allows for easy scalability by enabling the addition of new states without modifying existing code. 
        To add a new state, you simply create a new class that implements the OrderState interface and define its behavior. 
        This adheres to the Open/Closed Principle, as existing code remains unchanged while new functionality is added, making the system more flexible and adaptable to future requirements.

*/