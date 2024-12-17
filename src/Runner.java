public class Runner {

    public static void main(String[] args) {

        VendingMachine vendingMachine = new VendingMachine(new User(), new ServicerOfVendingMachine());
        vendingMachine.startMenu();
    }
}
