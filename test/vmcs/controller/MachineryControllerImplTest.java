package vmcs.controller;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import org.junit.Before;
import vmcs.model.Coin;
import vmcs.model.Drink;
import vmcs.model.MaintainerState;
import vmcs.physical.MachineImpl;
import vmcs.ui.MachineryPanelImpl;

public class MachineryControllerImplTest {

    List<Coin> coins = new ArrayList<>();
    List<Drink> drinks = new ArrayList<>();
    MachineryController machineryController;

    @Before
    public void setUp() {
        MachineImpl.getMachine().initStocks(coins, drinks);
        machineryController = MachineryPanelImpl.getInstance().getController();
        machineryController.lockDoor();
        MaintainerState.getInstance().setLogIn(false);
    }
    
    @Test
    public void changeDrinkStock() {
        Drink drink = new Drink();
        drink.setName("Test");
        drink.setQuantity(1);
        drink.setValue(1);
        MachineImpl.getMachine().getAllDrinks().add(drink);
        assertEquals(MachineImpl.getMachine().getAllDrinks().get(0).getQuantity(), 1);
        machineryController.changeDrinkStock(drink, 3);
        assertEquals(MachineImpl.getMachine().getAllDrinks().get(0).getQuantity(), 1);  //machinery is locked cant change
        machineryController.unLockDoor(); 
        machineryController.changeDrinkStock(drink, 3);
        assertEquals(MachineImpl.getMachine().getAllDrinks().get(0).getQuantity(), 1);//maintainer havent log in cannot update
        MaintainerState.getInstance().setLogIn(true);
        machineryController.unLockDoor(); 
        machineryController.changeDrinkStock(drink, 3);
        assertEquals(MachineImpl.getMachine().getAllDrinks().get(0).getQuantity(), 3);
        machineryController.changeDrinkStock(drink, 21);
        assertEquals(MachineImpl.getMachine().getAllDrinks().get(0).getQuantity(), 3); //max range 0 - 20
    }

    @Test
    public void changeCoinStock() {
        Coin coin = new Coin();
        coin.setName("Name");
        coin.setQuantity(1);
        coin.setValue(1);
        MachineImpl.getMachine().getAllCoins().add(coin);
        assertEquals(MachineImpl.getMachine().getAllCoins().get(0).getQuantity(), 1);
        machineryController.changeCoinStock(coin, 3);
        assertEquals(MachineImpl.getMachine().getAllCoins().get(0).getQuantity(), 1); //machinery is lock cant change
        machineryController.unLockDoor(); 
        machineryController.changeCoinStock(coin, 3);
        assertEquals(MachineImpl.getMachine().getAllCoins().get(0).getQuantity(), 1);//maintainer havent log in cannot update
        MaintainerState.getInstance().setLogIn(true);
        machineryController.unLockDoor(); 
        machineryController.changeCoinStock(coin, 3);
        assertEquals(MachineImpl.getMachine().getAllCoins().get(0).getQuantity(), 3);
        machineryController.changeCoinStock(coin, 41);
        assertEquals(MachineImpl.getMachine().getAllCoins().get(0).getQuantity(), 3); //max range 0 - 40
    }

    @Test
    public void lockDoor() {
        assertTrue(MachineImpl.getMachine().isDoorLock());
        machineryController.unLockDoor(); //maintainer havent log in, cannot unlock
        assertTrue(MachineImpl.getMachine().isDoorLock());
        MaintainerState.getInstance().setLogIn(true);
        machineryController.unLockDoor();
        assertFalse(MachineImpl.getMachine().isDoorLock());
        machineryController.lockDoor();
        assertTrue(MachineImpl.getMachine().isDoorLock());
    }

    @Test
    public void unLockDoor() {
        assertTrue(MachineImpl.getMachine().isDoorLock());
        machineryController.unLockDoor(); //maintainer havent log in, cannot unlock
        assertTrue(MachineImpl.getMachine().isDoorLock());
        MaintainerState.getInstance().setLogIn(true);
        machineryController.unLockDoor();
        assertFalse(MachineImpl.getMachine().isDoorLock());
        machineryController.lockDoor();
        assertTrue(MachineImpl.getMachine().isDoorLock());
    }
}