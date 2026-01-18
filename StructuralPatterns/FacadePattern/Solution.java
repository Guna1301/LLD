package StructuralPatterns.FacadePattern;

class PaymentService{
    public void makePayment(String account, double amount){
        System.out.println("Payment of " + amount + " made from account: " + account);
    }
}

class SeatReservationService{
    public void reserveSeat(String movieId, String seatNumber){
        System.out.println("Seat " + seatNumber + " reserved for movie: " + movieId);
    }
}

class NotificationService{
    public void sendBookingConfirmation(String userEmail, String details){
        System.out.println("Booking confirmation sent to " + userEmail + " with details: " + details);
    }
}


class LoyalityPointsService{
    public void addPoints(String userId, int points){
        System.out.println(points + " loyalty points added to user: " + userId);
    }
}

class TicketService{
    public void generateTicket(String movieId, String seatNumber){
        System.out.println("Ticket generated for movie: " + movieId + ", Seat: " + seatNumber);
    }
}


class MovieBookingFacade{
    private PaymentService paymentService;
    private SeatReservationService seatReservationService;
    private NotificationService notificationService;
    private LoyalityPointsService loyalityPointsService;
    private TicketService ticketService;

    // constructor to initialize all services
    public MovieBookingFacade(){
        this.paymentService = new PaymentService();
        this.seatReservationService = new SeatReservationService();
        this.notificationService = new NotificationService();
        this.loyalityPointsService = new LoyalityPointsService();
        this.ticketService = new TicketService();
    }

    // method providing a simplified interace for booking a movie ticket
    public void bookMovieTicket(String account, double amount, String movieId, String seatNumber, String userEmail, String userId){
        // step 1: make payment
        paymentService.makePayment(account, amount);

        // step 2: reserve seat
        seatReservationService.reserveSeat(movieId, seatNumber);

        // step 3: send booking confirmation
        notificationService.sendBookingConfirmation(userEmail, "Your booking is confirmed for " + movieId + ", Seat " + seatNumber);

        // step 4: add loyalty points
        loyalityPointsService.addPoints(userId, 50);

        // step 5: generate ticket
        ticketService.generateTicket(movieId, seatNumber);
    }
}

public class Solution {
    public static void main(String[] args) {
        MovieBookingFacade facade = new MovieBookingFacade();
        facade.bookMovieTicket("user_account_123", 250.0, "movie_456", "A10", "user@example.com", "user_123");
    }
}

/*
how this facade pattern solves the problem:
- provide a simpel unified interface (bookMovieTicket method) to a complex subsystem (multiple services involved in booking a movie ticket
- hide complexity of internal service calls from the client
- recude coupling so changes in internal services don't affect the client code
- centraliza the workflow logic, making it easier to maintain and modify

*/