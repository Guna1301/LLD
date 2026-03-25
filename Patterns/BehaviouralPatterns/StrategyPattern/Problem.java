
class RideMatchingService{
    public void matchRider(String riderLocation, String matchingType){
        // matchingType could be "nearest", "cheapest", "fastest"
        if(matchingType.equals("nearest")){
            // logic to find nearest driver
            System.out.println("Finding nearest driver for rider at " + riderLocation);
        } else if(matchingType.equals("cheapest")){
            // logic to find cheapest driver
            System.out.println("Finding cheapest driver for rider at " + riderLocation);
        } else if(matchingType.equals("fastest")){
            // logic to find fastest driver
            System.out.println("Finding fastest driver for rider at " + riderLocation);
        }else{
            System.out.println("Unknown matching type");
        }
    }
}

public class Problem {
    public static void main(String[] args) {
        RideMatchingService service = new RideMatchingService();
        service.matchRider("Downtown", "nearest");
        service.matchRider("Uptown", "cheapest");
        service.matchRider("airport", "fastest");
    }
}

/*
issues with this approach:
- violation of Open/Closed Principle: Adding new matching strategies requires modifying the RideMatchingService class.

- code becomes bulky and hard to maintain as more strategies are added.

- deficult to test or reuse individual matching strategies.

- no separation of concerns: matching logic is mixed within the service class.

*/