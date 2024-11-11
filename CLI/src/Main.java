import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter total tickets available: ");
        int totalTickets = scanner.nextInt();

        System.out.print("Enter ticket release rate: ");
        int ticketReleaseRate = scanner.nextInt();

        System.out.print("Enter customer retrieval rate: ");
        int customerRetrievalRate = scanner.nextInt();

        System.out.print("Enter max ticket capacity: ");
        int maxTicketCapacity = scanner.nextInt();
    }
}