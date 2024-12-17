import java.util.Scanner;

public class CreditCardReader implements PaymentSystem {

    private static CreditCardReader INSTANCE;

    private CreditCardReader() {}

    public static CreditCardReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CreditCardReader();
        }
        return INSTANCE;
    }

    @Override
    public boolean takeMoney(int money) {
        boolean moneyAccepted = false;
        if (money <= 0) {
            System.out.println("Sorry, payment by this credit card was rejected. Please try again.");
        } else {
            moneyAccepted = true;
            System.out.println("You payed $" + money + " by credit card.");
        }
        return moneyAccepted;
    }

    @Override
    public void giveMoneyBack(int money) {
        System.out.println("To return money by credit card please contact the bank.");
    }
}
