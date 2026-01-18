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

public class Problem {

    // booking movie ticker manually without facade
    public static void main(String args[]){
        // step 1: make payment
        PaymentService paymentService = new PaymentService();
        paymentService.makePayment("user_account_123", 250.0);

        // step 2: reserve seat
        SeatReservationService seatReservationService = new SeatReservationService();
        seatReservationService.reserveSeat("movie_456", "A10");

        // step 3: send booking confirmation
        NotificationService notificationService = new NotificationService();
        notificationService.sendBookingConfirmation("user@example.com", "Your booking is confirmed for movie_456, Seat A10");

        // step 4: add loyalty points
        LoyalityPointsService loyalityPointsService = new LoyalityPointsService();
        loyalityPointsService.addPoints("user_123", 50);

        // step 5: generate ticket
        TicketService ticketService = new TicketService();
        ticketService.generateTicket("movie_456", "A10");

    }
}
/*
    while this code works, its tightly coupled. the main class is manully calling each
    subsyte, service in the correct order and with the params

    this leads to:
    high complexity for the client
    duplicate code if you have to do this in multiple places
    violets srp 
*/