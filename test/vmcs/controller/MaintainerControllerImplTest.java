package vmcs.controller;

import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;
import vmcs.model.Coin;
import vmcs.model.Drink;
import vmcs.model.MaintainerState;
import vmcs.physical.MachineImpl;
import vmcs.ui.MaintenancePanelImpl;

public class MaintainerControllerImplTest {

    List<Coin> coins = new ArrayList<>();
    List<Drink> drinks = new ArrayList<>();
    MaintainerController maintainerController;

    @Before
    public void setUp() {
        MachineImpl.getMachine().initStocks(coins, drinks);
        maintainerController = MaintenancePanelImpl.getInstance().getController();
        maintainerController.setSelectedDrink(null);
        MaintainerState.getInstance().setLogIn(false);
    }

    @Test
    public void setSelectedDrink() {
        assertNull(maintainerController.getSelectedDrink());
        Drink drink = new Drink();
        drink.setName("Test");
        drink.setQuantity(1);
        drink.setValue(1);
        maintainerController.setSelectedDrink(drink);
        assertNotNull(maintainerController.getSelectedDrink());
        assertEquals(drink, maintainerController.getSelectedDrink());
    }

    @Test
    public void lock() {
        assertFalse(MaintainerState.getInstance().isLogIn());
        maintainerController.logIn();
        assertTrue(MaintainerState.getInstance().isLogIn());
        assertTrue(MaintainerState.getInstance().isLogIn());
        maintainerController.lock();
        assertFalse(MaintainerState.getInstance().isLogIn());
    }

    @Test
    public void logIn() {
        assertFalse(MaintainerState.getInstance().isLogIn());
        maintainerController.logIn();
        assertTrue(MaintainerState.getInstance().isLogIn());
    }

    @Test
    public void unLogIn() {
        assertFalse(MaintainerState.getInstance().isLogIn());
        maintainerController.logIn();
        assertTrue(MaintainerState.getInstance().isLogIn());
        assertTrue(MaintainerState.getInstance().isLogIn());
        maintainerController.unLogIn();
        assertFalse(MaintainerState.getInstance().isLogIn());
    }

    @Test
    public void checkPassword() {
        String correctPassword = "password123";
        String incorrectPassword = "111";
        assertFalse(MaintainerState.getInstance().isLogIn());
        maintainerController.checkPassword(correctPassword);
        assertTrue(MaintainerState.getInstance().isLogIn());
        maintainerController.unLogIn();
        assertFalse(MaintainerState.getInstance().isLogIn());
        maintainerController.checkPassword(incorrectPassword);
        assertFalse(MaintainerState.getInstance().isLogIn());
    }

    @Test
    public void validatePassword() {
        String correctPassword = "password123";
        String incorrectPassword = "111";
        assertTrue(maintainerController.validatePassword(correctPassword));
        assertFalse(maintainerController.validatePassword(incorrectPassword));
    }

    @Test
    public void collectAllCash() {
        int insertedCoinQuantity = 0;
        for (int i = 0; i < 100; i++) {
            Coin coin = new Coin();
            coin.setName(i + "");
            coin.setQuantity(i);
            coin.setValue(i);
            insertedCoinQuantity += i;
            MachineImpl.getMachine().getAllCoins().add(coin);
        }
        int coinQuantityBefore = 0;
        for (Coin coin : MachineImpl.getMachine().getAllCoins()) {
            coinQuantityBefore += coin.getQuantity();
        }
        assertEquals(insertedCoinQuantity, coinQuantityBefore);
        maintainerController.collectAllCash();
        int coinQuantityAfter = 0;
        for (Coin coin : MachineImpl.getMachine().getAllCoins()) {
            coinQuantityAfter += coin.getQuantity();
        }
        assertNotEquals(coinQuantityBefore, coinQuantityAfter);
        assertTrue(coinQuantityAfter == 0);
    }

    @Test
    public void showTotalCashHeld() {
        int insertedTotalCash = 0;
        for (int i = 0; i < 100; i++) {
            Coin coin = new Coin();
            coin.setName(i + "");
            coin.setQuantity(i);
            coin.setValue(i);
            insertedTotalCash += (i*i);
            MachineImpl.getMachine().getAllCoins().add(coin);
        }
        int totalCash = maintainerController.showTotalCashHeld();
        assertEquals(insertedTotalCash, totalCash);
    }

    @Test
    public void changePrice() {
        Drink drink = new Drink();
        drink.setName("Test");
        drink.setQuantity(1);
        drink.setValue(1);
        MachineImpl.getMachine().getAllDrinks().add(drink);
        assertEquals(MachineImpl.getMachine().getAllDrinks().get(0).getValue(), 1);
        maintainerController.changePrice("50");
        assertNotEquals(MachineImpl.getMachine().getAllDrinks().get(0).getValue(), 50); //no drink selected cant change
        maintainerController.setSelectedDrink(drink);
        maintainerController.changePrice("50");
        assertEquals(MachineImpl.getMachine().getAllDrinks().get(0).getValue(), 50);
    }
}
