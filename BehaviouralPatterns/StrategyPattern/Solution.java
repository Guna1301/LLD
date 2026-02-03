// strategy interface
interface MatchingStrategy {
    void match(String riderLocation);
}

// concrete strategy for nearest matching
class NearestMatchingStrategy implements MatchingStrategy {
    @Override
    public void match(String riderLocation) {
        System.out.println("Finding nearest driver for rider at " + riderLocation);
    }
}

// concrete strategy for cheapest matching
class CheapestMatchingStrategy implements MatchingStrategy {
    @Override
    public void match(String riderLocation) {
        System.out.println("Finding cheapest driver for rider at " + riderLocation);
    }
}

// concrete strategy for fastest matching
class FastestMatchingStrategy implements MatchingStrategy {
    @Override
    public void match(String riderLocation) {
        System.out.println("Finding fastest driver for rider at " + riderLocation);
    }
}

// context class
class RideMatchingService{
    private MatchingStrategy strategy;

    public RideMatchingService(MatchingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(MatchingStrategy strategy) {
        this.strategy = strategy;
    }

    public void matchRider(String riderLocation){
        strategy.match(riderLocation);
    }
}


public class Solution {
    public static void main(String[] args) {
        RideMatchingService service = new RideMatchingService(new NearestMatchingStrategy());
        service.matchRider("Downtown");

        service.setStrategy(new CheapestMatchingStrategy());
        service.matchRider("Uptown");

        service.setStrategy(new FastestMatchingStrategy());
        service.matchRider("Airport");
    }
    
}

/*
problem: violation of ocp
solution here: new strategies can be added without modifying the existing service codem just create a new class implementing the MatchingStrategy interface.

problem: code becomes bulky and hard to maintain
solution here: each strategy is encapsulated in its own class, making the code cleaner and easier to maintain. eliminates complex if else or switch statements.

problem: difficult to test or reuse individual matching strategies
solution here: each strategy can be tested independently, and can be reused in different contexts without duplicating code.

problem: no separation of concerns
solution here: the matching logic is separated from the service class, adhering to the Single Responsibility Principle.

*/
