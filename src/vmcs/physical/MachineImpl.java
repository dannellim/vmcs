package vmcs.physical;

import vmcs.model.Coin;
import vmcs.model.DoorState;
import vmcs.model.Drink;

import java.util.ArrayList;
import java.util.List;

public class MachineImpl implements DrinkInterface.DrinkInterfaceListener, CoinInterface.CoinInterfaceListener, Machine, DoorState.DoorStateChangeListener {

    private CoinInterface coinInterface;
    private DrinkInterface drinkInterface;

    private final DoorState doorState;
    private final List<CoinInterface.CoinInterfaceListener> coinInterfaceListeners;
    private final List<DrinkInterface.DrinkInterfaceListener> drinksInterfaceListeners;
    private List<DoorState.DoorStateChangeListener> doorStateChangeListeners;

    private static volatile Machine sSoleInstance;

    public static Machine getMachine() {
        if (sSoleInstance == null) {
            synchronized (Machine.class) {
                if (sSoleInstance == null) {
                    sSoleInstance = new MachineImpl();
                }
            }
        }
        return sSoleInstance;
    }

    @Override
    public void addNewCoinInterfaceStatListener(CoinInterface.CoinInterfaceListener coinInterfaceListener) {
        if (coinInterfaceListener != null) {
            this.coinInterfaceListeners.add(coinInterfaceListener);
        }
    }

    @Override
    public void addDoorStateInterfaceStatListener(DoorState.DoorStateChangeListener doorStateChangeListener) {
        if (doorStateChangeListener != null) {
            this.doorStateChangeListeners.add(doorStateChangeListener);

        }
    }

    @Override
    public void addNewDrinkInterfaceStatListener(DrinkInterface.DrinkInterfaceListener drinkInterfaceListener) {
        if (drinkInterfaceListener != null) {
            this.drinksInterfaceListeners.add(drinkInterfaceListener);
        }
    }

    public MachineImpl() {
        coinInterfaceListeners = new ArrayList<>();
        drinksInterfaceListeners = new ArrayList<>();
        doorStateChangeListeners=new ArrayList<>();
        doorState = DoorState.getInstance(this);
      
    }

    @Override
    public void initStocks(List<Coin> coins, List<Drink> drinks) {
        coinInterface = new CoinInterface(this, coins);
        drinkInterface = new DrinkInterface(this, drinks);
    }

    @Override
    public void onDrinkDispensed(Drink drink) {
        drinksInterfaceListeners.forEach(drinkInterfaceListener -> {
            drinkInterfaceListener.onDrinkDispensed(drink);
        });
    }

    @Override
    public void dispenseDrink(Drink drink) {
        drinkInterface.dispenseDrink(drink);
    }

    @Override
    public void updateDrinkStock(Drink drink, int qty) {
        drinkInterface.updateDrinkStock(drink, qty);
    }

    @Override
    public void updateDrinkPrice(Drink drink, int price) {
        drinkInterface.updatePriceStock(drink,price);
    }

    @Override
    public void updateCoinStock(Coin coin, int qty) {
        coinInterface.updateCoinStock(coin, qty);
    }

    @Override
    public void acceptCoin(Coin coin) {
        coinInterface.insertCoin(coin);
    }

    @Override
    public void onDrinkStockChanged(Drink drink) {
        drinksInterfaceListeners.forEach(drinkInterfaceListener -> {
            drinkInterfaceListener.onDrinkStockChanged(drink);
        });
    }

    @Override
    public void onCoinAccepted(Coin coin) {
        coinInterfaceListeners.forEach(coinInterfaceListener -> {
            coinInterfaceListener.onCoinAccepted(coin);
        });
    }

    @Override
    public void onCoinRejected(Coin coin) {
        coinInterfaceListeners.forEach(coinInterfaceListener -> {
            coinInterfaceListener.onCoinRejected(coin);
        });
    }

    @Override
    public void onCoinDispensed(Coin coin) {
        coinInterfaceListeners.forEach(coinInterfaceListener -> {
            coinInterfaceListener.onCoinDispensed(coin);
        });
    }

    @Override
    public void onCoinDispensed(List<Coin> coin) {
        coinInterfaceListeners.forEach(coinInterfaceListener -> {
            coinInterfaceListener.onCoinDispensed(coin);
        });
    }

    @Override
    public void onCoinStockChanged(Coin coin) {
        coinInterfaceListeners.forEach(coinInterfaceListener -> {
            coinInterfaceListener.onCoinStockChanged(coin);
        });
    }

    @Override
    public void onDoorStateChange(boolean isLock) {
        doorStateChangeListeners.forEach(doorStateChangeListener -> {
            doorStateChangeListener.onDoorStateChange(isLock);
        });
    }

    @Override
    public List<Drink> getAllDrinks() {
        return drinkInterface.getDrinkStock();
    }

    @Override
    public List<Coin> getAllCoins() {
        return coinInterface.getCoinStock();
    }

    @Override
    public void dispense(Coin coin) {
        coinInterface.dispense(coin);
    }

    @Override
    public boolean isDoorLock() {
        return doorState.isLocked();
    }

    @Override
    public void unlockDoor() {
         doorState.setLocked(false);
    }

    @Override
    public void lockDoor() {
         doorState.setLocked(true);
    }

    @Override
    public void dispense(List<Coin> coins) {
        coinInterface.dispense(coins);
    }
}
