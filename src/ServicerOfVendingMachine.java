import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServicerOfVendingMachine {

    public int addOrRemoveCoins(int coins) {
        int coinsUpdated = coins;
        if (coins <= 10) {
            coinsUpdated += 50;
            System.out.println("Coins $50 were added. Sum of coins is $" + coinsUpdated);
        } else if (coins > 50) {
            int coinsToRemove = coinsUpdated - 50;
            coinsUpdated = 50;
            System.out.println("Coins $" + coinsToRemove + " were removed. Sum of coins is $50");
        }
        return coinsUpdated;
    }

    public int removeBills(int bills) {
        if(bills > 0) {
            System.out.println("Bills $" + bills + " were removed. Sum of bills is $0");
        }
        return 0;
    }

    public Map<Integer, List<Product>> addProducts(Map<Integer, List<Product>> codeProductsMapToAdd){
        List<Product> listOfBars = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            listOfBars.add(new Product("Granola Bar", 6));
        }
        List<Product> listOfChips = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            listOfChips.add(new Product("Potato Chips", 7));
        }
        List<Product> listOfCookies = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            listOfCookies.add(new Product("ChocolateCookies", 5));
        }
        List<Product> listOfCrackers = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            listOfCrackers.add(new Product("Cheese Crackers", 8));
        }
        List<Product> listOfStillWater = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            listOfStillWater.add(new Product("Still Water, 0.5L", 2));
        }
        List<Product> listOfCocaCola = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            listOfCocaCola.add(new Product("Coca-Cola, 0.5L", 4));
        }
        codeProductsMapToAdd.put(1, listOfBars);
        codeProductsMapToAdd.put(2, listOfChips);
        codeProductsMapToAdd.put(3, listOfCookies);
        codeProductsMapToAdd.put(4, listOfCrackers);
        codeProductsMapToAdd.put(5, listOfStillWater);
        codeProductsMapToAdd.put(6, listOfCocaCola);
        System.out.println("Products were added.");
        return codeProductsMapToAdd;
    }
}
