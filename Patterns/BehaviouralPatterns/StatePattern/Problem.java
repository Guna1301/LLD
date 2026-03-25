package BehaviouralPatterns.StatePattern;
class Order{
    private String state;

    public Order(){
        this.state = "ORDER_PLACED";
    }

    // method to cancle the order
    // only allows to cancle if it is in ORDER_PLACED state or PREPARING state
    public void cancelOrder(){
        if(state.equals("ORDER_PLACED") || state.equals("PREPARING")){
            this.state = "CANCELLED";
            System.out.println("Order cancelled successfully.");
        }else{
            System.out.println("Order cannot be cancelled in the current state: " + state);
        }
    }

    // moving order state to next state
    public void nextState(){
        switch(state){
            case "ORDER_PLACED":
                this.state = "PREPARING";
                break;
            case "PREPARING":
                this.state = "OUT_FOR_DELIVERY";
                break;
            case "OUT_FOR_DELIVERY":
                this.state = "DELIVERED";
                break;
            default:
                System.out.println("Order is in an invalid state: " + state);
                return;
        }
        System.out.println("Order state changed to: " + state);
    }

    public String getState() {
        return state;
    }
}
public class Problem {
    public static void main(String[] args) {
        Order order = new Order();
        System.out.println("Current Order State: " + order.getState());

        order.nextState(); // Move to PREPARING
        order.nextState(); // Move to OUT_FOR_DELIVERY
        order.cancelOrder(); // Attempt to cancel (will fail)
        order.nextState(); // Move to DELIVERED
        order.cancelOrder(); // Attempt to cancel (will fail)
        System.out.println("Final Order State: " + order.getState());
    }
}


/*
Issues with this code:
1. state change management:
    - The Order class is responsible for managing its state and the transitions between states. This can lead to a violation of the Single Responsibility Principle, as the Order class is handling both the business logic of the order and the state management.
2. lack of encapsulation:
    - The state of the order is represented as a string, which can lead to issues with encapsulation and maintainability. It would be better to use an enum or a separate class to represent the different states of the order.
3. code duplication:
    - The logic for changing the state of the order is duplicated in the nextState() method, which can lead to code duplication and make it harder to maintain.
4. difficulty in adding new states:
    - If we want to add new states to the order, we would need to modify the nextState() method and potentially other parts of the code, which can lead to bugs and maintenance issues. It would be better to use a design pattern like the State pattern to manage the different states of the order more effectively.

*/