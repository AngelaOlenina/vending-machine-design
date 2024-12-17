import java.util.*;

public class VendingMachine {

    int sumOfBills;
    int sumOfCoins;
    int codeOfProductToDispense;
    Map<Integer, List<Product>> codeProductsMap = new HashMap<>();

    PaymentSystem paymentSystem;
    User user;
    ServicerOfVendingMachine servicerOfVendingMachine;

    public VendingMachine(User user, ServicerOfVendingMachine servicerOfVendingMachine) {
        this.user = user;
        this.servicerOfVendingMachine = servicerOfVendingMachine;
        sumOfCoins = servicerOfVendingMachine.addOrRemoveCoins(sumOfCoins);
        codeProductsMap = servicerOfVendingMachine.addProducts(codeProductsMap);
    }

    public void setPaymentSystem(PaymentSystem paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    public void printMenu() {
        System.out.println("Sum of bills is $" + sumOfBills);
        System.out.println("Sum of coins is $" + sumOfCoins);

        System.out.println("Welcome! Please choose a variant below:");
        System.out.println("1 - buy a product");
        System.out.println("2 - call a servicer of vending machine");
        System.out.println("3 - quit");
        System.out.println("Enter number: ");
    }

    public void startMenu() {
        System.out.println("*** Start of work ***");
        printMenu();
        int choiceMenuNumber;
        while ((choiceMenuNumber = user.enterNumber()) != 3) {
            switch (choiceMenuNumber) {
                case 1 -> buyProduct();
                case 2 -> callServicer();
                default -> System.out.println("Sorry, this menu number is not correct. Please enter a new number.");
            }
        }
        System.out.println("*** End of work ***");
    }

    public void buyProduct() {
        displayProducts();
        int priceOfProductToPay = chooseProductToPay();
        if (priceOfProductToPay == -1) {
            return;
        }
        int result = choosePaymentSystem();
        if (result == -1) {
            return;
        }
        int change = calculateChange(priceOfProductToPay);
        if (change == -1) {
            return;
        } else if (change > 0) {
            returnChange(change);
        }
        dispenseProduct(codeOfProductToDispense);
        System.out.println("*** Return to menu ***");
        printMenu();
    }

    public void callServicer() {
        sumOfCoins = servicerOfVendingMachine.addOrRemoveCoins(sumOfCoins);
        sumOfBills = servicerOfVendingMachine.removeBills(sumOfBills);
        codeProductsMap = servicerOfVendingMachine.addProducts(codeProductsMap);
        System.out.println("*** Return to menu ***");
        printMenu();
    }

    public void displayProducts() {
        System.out.println("Choose a product.");
        for (Map.Entry<Integer, List<Product>> entry : codeProductsMap.entrySet()) {
            int code = entry.getKey();
            List<Product> productsList = entry.getValue();
            System.out.println("code: " + code + " - " + productsList);
        }
        System.out.println("Enter a product code:");
    }

    public int chooseProductToPay() {
        int productCode = user.enterNumber();
        int priceOfProductToPay = 0;
        while (!codeProductsMap.containsKey(productCode)) {
            System.out.println("Sorry, this product code is not correct. Please enter a new code.");
            productCode = user.enterNumber();
        }
        if (codeProductsMap.get(productCode).isEmpty()) {
            System.out.println("Sorry, product is out of stock. Please call a servicer of vending machine.");
            System.out.println("*** Return to menu ***");
            printMenu();
            return -1;
        } else {
            codeOfProductToDispense = productCode;
            Product product = codeProductsMap.get(productCode).get(0);
            priceOfProductToPay = product.getPrice();
            System.out.println("You chose a product " + product.getName() + " with price $" + priceOfProductToPay);
        }
        return priceOfProductToPay;
    }

    public int choosePaymentSystem() {
        int result = 0;
        System.out.println("Choose a payment system:");
        System.out.println("1 - credit card");
        System.out.println("2 - bill");
        System.out.println("3 - coin");
        System.out.println("4 - quit");
        int choicePaymentNumber = user.enterNumber();
        if (choicePaymentNumber == 1) {
            setPaymentSystem(CreditCardReader.getInstance());
            System.out.println("You chose to pay by credit card.");
        } else if (choicePaymentNumber == 2) {
            setPaymentSystem(BillAcceptor.getInstance());
            System.out.println("You chose to pay by bills.");
        } else if (choicePaymentNumber == 3) {
            setPaymentSystem(CoinAcceptor.getInstance());
            System.out.println("You chose to pay by coins.");
        } else if (choicePaymentNumber == 4) {
            System.out.println("You chose to return to menu.");
            System.out.println("*** Return to menu ***");
            printMenu();
            result = -1;
        } else {
            System.out.println("Sorry, this number is not correct.");
            System.out.println("*** Return to menu ***");
            printMenu();
            result = -1;
        }
        return result;
    }

    public int acceptPayment(int sumToPay) {
        int moneyPaid;
        boolean moneyAccepted;
        do {
            if (paymentSystem instanceof CreditCardReader) {
                moneyPaid = user.makeAPaymentByCreditCard(sumToPay);
            } else {
                moneyPaid = user.makeAPaymentByBillsAndCoins();
            }
            moneyAccepted = paymentSystem.takeMoney(moneyPaid);
        } while (!moneyAccepted);
        if (paymentSystem instanceof BillAcceptor) {
            sumOfBills += moneyPaid;
        } else if (paymentSystem instanceof CoinAcceptor) {
            sumOfCoins += moneyPaid;
        }
        return moneyPaid;
    }

    public int calculateChange(int sumToPay) {
        int sumPaid = 0;
        System.out.println("Please pay $" + sumToPay);
        sumPaid += acceptPayment(sumToPay);
        while (sumPaid < sumToPay) {
            System.out.printf("You paid $%d total, you have to pay another $%d \n", sumPaid, (sumToPay - sumPaid));
            sumPaid += acceptPayment(sumToPay);
        }
        System.out.println("You payed $" + sumPaid + " total.");
        int changeToReturn = sumPaid - sumToPay;
        if (changeToReturn > sumOfCoins) {
            System.out.println("Sorry, there is no enough change. Returning your cash.");
            paymentSystem.giveMoneyBack(sumPaid);
            if (paymentSystem instanceof BillAcceptor) {
                sumOfBills -= sumPaid;
            } else if (paymentSystem instanceof CoinAcceptor) {
                sumOfCoins -= sumPaid;
            }
            System.out.println("*** Return to menu ***");
            printMenu();
            return -1;
        } else {
            System.out.println("Your change is $" + changeToReturn);
        }
        return changeToReturn;
    }

    public void returnChange(int changeToReturn) {
        CoinAcceptor coinAcceptor = CoinAcceptor.getInstance();
        coinAcceptor.giveMoneyBack(changeToReturn);
        sumOfCoins -= changeToReturn;
    }

    public void dispenseProduct(int code) {
        Product product = codeProductsMap.get(code).remove(0);
        System.out.println("Product " + product.getName() + " with price $" + product.getPrice() + " was dispensed.");
    }
}
