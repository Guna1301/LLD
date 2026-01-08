package CreationalPatterns.FactoryPattern;

interface Logistics {
    void deliver();
}

class Road implements Logistics {
    public void deliver() {
        System.out.println("Deliver by land in a box.");
    }
}

class Air implements Logistics {
    public void deliver() {
        System.out.println("Deliver by air in a container.");
    }
}


class LogisticsService{
    public void send(String mode){
        if(mode.equals("Air")){
            Air air = new Air();
            air.deliver();
        } else if (mode.equals("Road")){
            Road road = new Road();
            road.deliver();
        } else {
            System.out.println("Unknown mode of transport.");
        }
    }
}

public class BadStructure {
    public static void main(String args[]){
        LogisticsService service = new LogisticsService();
        service.send("Air");
        service.send("Road");
    }
}
// The above code has several issues:
/*

-> the object of air and road are directly instatated based on string comparison
-> the object creation logic is inside the service class

1. Violation of Open/Closed Principle: The LogisticsService class is not closed for modification. Every time a new mode of transport is added, the send method needs to be modified, which violates the Open/Closed Principle of SOLID design principles.
2. Tight Coupling: The LogisticsService class is tightly coupled with the concrete implementations of Logistics (Air and Road). This makes it difficult to extend or modify the code without changing the LogisticsService class.
3. Lack of Abstraction: The LogisticsService class directly instantiates the concrete classes (Air and Road) instead of relying on abstractions. This reduces flexibility and makes it harder to introduce new types of logistics without changing existing code.
4. Poor Maintainability: As the number of logistics modes increases, the send method will become increasingly complex and harder to maintain. Each new mode requires changes to the existing code, increasing the risk of introducing bugs.
*/