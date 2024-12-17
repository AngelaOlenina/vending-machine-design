public class BillAcceptor implements PaymentSystem {

    private static BillAcceptor INSTANCE;

    private BillAcceptor() {}

    public static BillAcceptor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BillAcceptor();
        }
        return INSTANCE;
    }

    @Override
    public boolean takeMoney(int money) {
        boolean moneyAccepted = false;
        if (money <= 0) {
            System.out.println("Sorry, payment by this bill was rejected. Please try again.");
        } else {
            moneyAccepted = true;
            System.out.println("You payed $" + money + " by bill.");
        }
        return moneyAccepted;
    }

    @Override
    public void giveMoneyBack(int money) {
        System.out.println("$" + money + " were returned by bill.");
    }
}
