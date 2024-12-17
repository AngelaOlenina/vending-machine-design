import java.util.Scanner;

public class CoinAcceptor implements PaymentSystem {

    private static CoinAcceptor INSTANCE;

    private CoinAcceptor() {}

    public static CoinAcceptor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CoinAcceptor();
        }
        return INSTANCE;
    }

    @Override
    public boolean takeMoney(int money) {
        boolean moneyAccepted = false;
        if (money <= 0) {
            System.out.println("Sorry, payment by this coin was rejected. Please try again.");
        } else {
            moneyAccepted = true;
            System.out.println("You payed $" + money + " by coin.");
        }
        return moneyAccepted;
    }

    @Override
    public void giveMoneyBack(int money) {
        System.out.println("$" + money + " were returned by coin.");
    }


}
