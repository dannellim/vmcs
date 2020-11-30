package vmcs.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import vmcs.model.Coin;
import vmcs.model.Drink;
import vmcs.physical.MachineImpl;
import vmcs.ui.CustomerPanelImpl;
import vmcs.ui.SimulatorControlPanel;
import vmcs.ui.SimulatorControlPanelImpl;

public class CustomerControllerImplTest {

    List<Coin> coins = new ArrayList<>();
    List<Drink> drinks = new ArrayList<>();
    CustomerController customerController;

    @Before
    public void setUp() {
        MachineImpl.getMachine().initStocks(coins, drinks);
        customerController = CustomerPanelImpl.getInstance().getController();
        System.out.println("1");
    }

    @Test
    public void getCoinStocks() {
        assertEquals(customerController.getCoinStocks().size(), 0);
        Coin coin = new Coin();
        coin.setName("Test");
        coin.setQuantity(1);
        coin.setValue(1);
        customerController.getCoinStocks().add(coin);
        assertEquals(customerController.getCoinStocks().size(), 1);
        Coin getCoin = customerController.getCoinStocks().get(0);
        assertEquals(coin, getCoin);
        System.out.println("2");
    }

    @Test
    public void insertCoin() {
        assertEquals(customerController.getCoinStocks().size(), 0);

        Drink drink = new Drink();
        drink.setName("Test");
        drink.setQuantity(1);
        drink.setValue(2);
        customerController.getDrinkStocks().add(drink);
        customerController.selectDrink(drink);
        
        Coin coin = new Coin();
        coin.setName("Test");
        coin.setQuantity(1);
        coin.setValue(1);
        customerController.getCoinStocks().add(coin);
        customerController.insertCoin(coin);
        assertEquals(customerController.getCoinStocks().size(), 1);
        Coin getCoin = customerController.getCoinStocks().get(0);
        assertEquals(coin, getCoin);
        
        coin = new Coin();
        coin.setName("Test2");
        coin.setQuantity(1);
        coin.setValue(0);//value 0 coin shud not be accepted
        customerController.insertCoin(coin);
        assertEquals(customerController.getCoinStocks().size(), 1);
        System.out.println("3");
    }

    @Test
    public void getDrinkStocks() {
        assertEquals(customerController.getDrinkStocks().size(), 0);
        Drink drink = new Drink();
        drink.setName("Test");
        drink.setQuantity(1);
        drink.setValue(1);
        customerController.getDrinkStocks().add(drink);
        assertEquals(customerController.getDrinkStocks().size(), 1);
        Drink getDrink = customerController.getDrinkStocks().get(0);
        assertEquals(drink, getDrink);
        System.out.println("4");
    }

    @Test
    public void selectDrink() {
        assertNull(customerController.getSelectedDrink());
        Drink drink = new Drink();
        drink.setName("Test");
        drink.setQuantity(1);
        drink.setValue(1);
        customerController.selectDrink(drink);
        assertNotNull(customerController.getSelectedDrink());
        System.out.println("5");
    }

    @Test
    public void terminateTransaction() {
        Drink drink = new Drink();
        drink.setName("Test");
        drink.setQuantity(1);
        drink.setValue(4);
        customerController.selectDrink(drink);
        assertNotNull(customerController.getSelectedDrink());
        customerController.terminateTransaction();
        assertNull(customerController.getSelectedDrink());

        customerController.selectDrink(drink);
        Coin coin = new Coin();
        coin.setName("Test");
        coin.setQuantity(1);
        coin.setValue(1);
        customerController.getCoinStocks().add(coin);
        customerController.insertCoin(coin);
        customerController.insertCoin(coin);
        assertEquals(customerController.getCoinStocks().get(0).getQuantity(), 3);
        customerController.insertCoin(coin);
        assertEquals(customerController.getCoinStocks().get(0).getQuantity(), 4);
        customerController.terminateTransaction();
        assertEquals(customerController.getCoinStocks().get(0).getQuantity(), 1);
        assertNull(customerController.getSelectedDrink());

        System.out.println("6");
    }
}
