package vmcs.physical;

import java.util.List;
import vmcs.model.Coin;
import vmcs.model.DoorState;
import vmcs.model.Drink;

public interface Machine {

    public void addNewCoinInterfaceStatListener(CoinInterFace.CoinInterfaceListener coinInterfaceListener);

    public void addNewDrinkInterfaceStatListener(CoinInterFace.CoinInterfaceListener coinInterfaceListener);

    public void updateDrinkStock(Drink drink, int qty);

    public void updateCoinStock(Coin coin, int qty);

    public void acceptCoin(Coin coin);

    public void dispenseDrink(Drink drink);

    public void addDoorStateInterfaceStatListener(DoorState.DoorStateChangeListener doorStateChangeListener);

    public void initStocks(List<Coin> coins, List<Drink> drinks);
    
    public List<Drink> getAllDrinks();

    public List<Coin> getAllCoins();
}
