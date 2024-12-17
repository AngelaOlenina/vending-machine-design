import java.util.Scanner;

public class User {

    Scanner scanner = new Scanner(System.in);

    public int enterNumber() {
        int choiceNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("You entered " + choiceNumber);
        return choiceNumber;
    }

    public int makeAPaymentByCreditCard(int sumToPayByCreditCard) {
        return sumToPayByCreditCard;
    }
    public int makeAPaymentByBillsAndCoins() {
        return Integer.parseInt(scanner.nextLine());
    }
}
