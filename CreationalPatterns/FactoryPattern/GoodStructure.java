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

class LogisticsFactory{
    public Logistics getLogistics(String mode){
        if(mode.equals("Air")){
            return new Air();
        } else if (mode.equals("Road")){
            return new Road();
        } 
        throw new IllegalArgumentException(String.format("Unknown mode of transport: %s", mode));
    }
}

class LogisticsService{
    public void send(String mode){
        LogisticsFactory factory = new LogisticsFactory();
        Logistics logistics = factory.getLogistics(mode);
        logistics.deliver();
    }
}
public class GoodStructure {
    public static void main(String args[]){
        LogisticsService service = new LogisticsService();
        service.send("Air");
        service.send("Road");
    }
}

// The above code addresses the issues present in the BadStructure example:
/*
-> the object creation logic is moved to a separate factory class (LogisticsFactory)
-> the logisticservice class now only focuses on using the logistics objects without worrying about their instantiation-> adding a new mode (e.g., ship) only requires
modifying the factory not the service,

benigits:
1. loose coupling
2. open/closed principle
3. better maintainability
4. separation of concerns
*/