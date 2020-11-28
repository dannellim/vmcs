package vmcs.physical;

import vmcs.model.Coin;
import vmcs.model.DoorState;
import vmcs.model.Drink;

import java.util.ArrayList;
import java.util.List;

public class FakeMachine implements DrinkInterface.DrinkInterfaceListener, CoinInterFace.CoinInterfaceListener, Machine, DoorState.DoorStateChangeListener {

    private CoinInterFace coinInterface;
    private DrinkInterface drinkInterface;

    private DoorState doorState;
    private List<CoinInterFace.CoinInterfaceListener> coinInterfaceListeners;
    private List<DrinkInterface.DrinkInterfaceListener> drinksInterfaceListeners;
    private List<DoorState.DoorStateChangeListener> doorStateChangeListeners;

    private static FakeMachine fakeMachine = null;

    public static FakeMachine getMachineInstance() {
        if (fakeMachine == null) {
            synchronized (FakeMachine.class) {
                if (fakeMachine == null) {
                    fakeMachine = new FakeMachine();
                }
            }
        }
        return fakeMachine;
    }

    @Override
    public void addNewCoinInterfaceStatListener(CoinInterFace.CoinInterfaceListener coinInterfaceListener) {
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
    public void addNewDrinkInterfaceStatListener(CoinInterFace.CoinInterfaceListener coinInterfaceListener) {
        if (coinInterfaceListener != null) {
            this.coinInterfaceListeners.add(coinInterfaceListener);
        }
    }

    private FakeMachine() {
        coinInterfaceListeners = new ArrayList<>();
        doorState = DoorState.getInstance(this);
    }

    @Override
    public void initStocks(List<Coin> coins, List<Drink> drinks) {
        coinInterface = new CoinInterFace(this, coins);
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
}
