package vmcs.physical;

import java.util.ArrayList;
import java.util.List;
import vmcs.model.Coin;
import vmcs.model.Drink;
import static junit.framework.TestCase.*;

public class MachineImplTest {

    private final List<Coin> coins = new ArrayList<>();
    private final List<Drink> drinks = new ArrayList<>();

    @org.junit.Test
    public void initStocks() {
        for (int i = 0; i < 100; i++) {
            Coin coin = new Coin();
            coin.setName(i + "");
            coin.setQuantity(i);
            coin.setValue(i);
            Drink drink = new Drink();
            drink.setName(i + "");
            drink.setQuantity(i);
            drink.setValue(i);
            coins.add(coin);
            drinks.add(drink);
        }
        MachineImpl.getMachine().initStocks(coins, drinks);
        List<Coin> coins2 = MachineImpl.getMachine().getAllCoins();
        List<Drink> drinks2 = MachineImpl.getMachine().getAllDrinks();
        for (int i = 0; i < 100; i++) {
            Coin coin1 = coins.get(i);
            Coin coin2 = coins2.get(i);
            assertEquals(coin1, coin2);
            Drink drink1 = drinks.get(i);
            Drink drink2 = drinks2.get(i);
            assertEquals(drink1, drink2);
        }
    }

    @org.junit.Test
    public void dispenseDrink() {
        Drink drink = new Drink();
        drink.setName("Test");
        drink.setQuantity(1);
        drink.setValue(0);
        drinks.add(drink);
        MachineImpl.getMachine().initStocks(coins, drinks);
        MachineImpl.getMachine().dispenseDrink(drink);
        Drink drinkOut = MachineImpl.getMachine().getAllDrinks().get(0);
        assertEquals(drinkOut.getQuantity(), 0);
    }

    @org.junit.Test
    public void updateDrinkStock() {
        Drink drink = new Drink();
        drink.setName("Test");
        drink.setQuantity(1);
        drink.setValue(0);
        drinks.add(drink);
        MachineImpl.getMachine().initStocks(coins, drinks);
        MachineImpl.getMachine().updateDrinkStock(drink, 100);
        Drink drinkUpdate = MachineImpl.getMachine().getAllDrinks().get(0);
        assertEquals(drinkUpdate.getQuantity(), 100);
    }

    @org.junit.Test
    public void updateDrinkPrice() {
        Drink drink = new Drink();
        drink.setName("Test");
        drink.setQuantity(1);
        drink.setValue(0);
        drinks.add(drink);
        MachineImpl.getMachine().initStocks(coins, drinks);
        MachineImpl.getMachine().updateDrinkPrice(drink, 100);
        Drink drinkUpdate = MachineImpl.getMachine().getAllDrinks().get(0);
        assertEquals(drinkUpdate.getValue(), 100);
    }

    @org.junit.Test
    public void updateCoinStock() {
        Coin coin = new Coin();
        coin.setName("Test");
        coin.setQuantity(1);
        coin.setValue(0);
        coins.add(coin);
        MachineImpl.getMachine().initStocks(coins, drinks);
        MachineImpl.getMachine().updateCoinStock(coin, 100);
        Coin coinUpdate = MachineImpl.getMachine().getAllCoins().get(0);
        assertEquals(coinUpdate.getQuantity(), 100);
    }

    @org.junit.Test
    public void acceptCoin() {
        Coin coin = new Coin();
        coin.setName("Test");
        coin.setQuantity(1);
        coin.setValue(1);
        coins.add(coin);
        MachineImpl.getMachine().initStocks(coins, drinks);
        MachineImpl.getMachine().acceptCoin(coin);
        Coin coinUpdate = MachineImpl.getMachine().getAllCoins().get(0);
        assertEquals(coinUpdate.getQuantity(), 2);

        coin = new Coin();
        coin.setName("Test");
        coin.setQuantity(1);
        coin.setValue(0); //if value is 0, is invalid coin, will not accept, quantity will not change
        coins.add(coin);
        MachineImpl.getMachine().acceptCoin(coin);
        coinUpdate = MachineImpl.getMachine().getAllCoins().get(1);
        assertEquals(coinUpdate.getQuantity(), 1);
    }

    @org.junit.Test
    public void getAllDrinks() {
        for (int i = 0; i < 100; i++) {
            Drink drink = new Drink();
            drink.setName(i + "");
            drink.setQuantity(i);
            drink.setValue(i);
            drinks.add(drink);
        }
        MachineImpl.getMachine().initStocks(coins, drinks);
        List<Drink> drinks2 = MachineImpl.getMachine().getAllDrinks();
        for (int i = 0; i < 100; i++) {
            Drink drink1 = drinks.get(i);
            Drink drink2 = drinks2.get(i);
            assertEquals(drink1, drink2);
        }
    }

    @org.junit.Test
    public void getAllCoins() {
        for (int i = 0; i < 100; i++) {
            Coin coin = new Coin();
            coin.setName(i + "");
            coin.setQuantity(i);
            coin.setValue(i);
            coins.add(coin);
        }
        MachineImpl.getMachine().initStocks(coins, drinks);
        List<Coin> coins2 = MachineImpl.getMachine().getAllCoins();
        for (int i = 0; i < 100; i++) {
            Coin coin1 = coins.get(i);
            Coin coin2 = coins2.get(i);
            assertEquals(coin1, coin2);
        }
    }

    @org.junit.Test
    public void dispense() {
        Coin coin = new Coin();
        coin.setName("Test");
        coin.setQuantity(1);
        coin.setValue(1);
        coins.add(coin);
        MachineImpl.getMachine().initStocks(coins, drinks);
        MachineImpl.getMachine().dispense(coin);
        Coin coinUpdate = MachineImpl.getMachine().getAllCoins().get(0);
        assertEquals(coinUpdate.getQuantity(), 0);
    }

    @org.junit.Test
    public void unlockDoor() {
        MachineImpl.getMachine().unlockDoor();
        boolean result = MachineImpl.getMachine().isDoorLock();
        assertFalse(result);
    }

    @org.junit.Test
    public void lockDoor() {
        MachineImpl.getMachine().lockDoor();
        boolean result = MachineImpl.getMachine().isDoorLock();
        assertTrue(result);
    }
}
