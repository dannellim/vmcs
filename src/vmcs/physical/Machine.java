package vmcs.physical;

import java.util.List;
import vmcs.model.Coin;
import vmcs.model.DoorState;
import vmcs.model.Drink;
import vmcs.model.MaintainerState;

public interface Machine {

    public void addNewCoinInterfaceStatListener(CoinInterface.CoinInterfaceListener coinInterfaceListener);

    public void addNewDrinkInterfaceStatListener(DrinkInterface.DrinkInterfaceListener drinkInterfaceListener);

    public void updateDrinkStock(Drink drink, int qty);

    public void updateDrinkPrice(Drink drink, int price);

    public void updateCoinStock(Coin coin, int qty);

    public void acceptCoin(Coin coin);

    public void dispenseDrink(Drink drink);

    public void addDoorStateInterfaceStatListener(DoorState.DoorStateChangeListener doorStateChangeListener);

    public void initStocks(List<Coin> coins, List<Drink> drinks);

    public List<Drink> getAllDrinks();

    public List<Coin> getAllCoins();

    public void dispense(Coin coin);

    public boolean isDoorLock();

    public void unlockDoor();

    public void lockDoor();

    public void dispense(List<Coin> coins);
}
