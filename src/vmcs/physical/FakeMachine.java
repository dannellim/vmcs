package vmcs.physical;


import vmcs.model.Coin;
import vmcs.model.DoorState;
import vmcs.model.Drink;

import java.util.ArrayList;
import java.util.List;

public class FakeMachine implements DrinkInterface.DrinkInterfaceListener, CoinInterFace.CoinInterfaceListener, Machine, DoorState.DoorStateChangeListener {
    private CoinInterFace coinInterFace;
    private DrinkInterface drinkInterface;

    private DoorState doorState;
    private List<CoinInterFace.CoinInterfaceListener> coinInterfaceListeners;
    private List<DrinkInterface.DrinkInterfaceListener> drinksInterfaceListeners;
    private List<DoorState.DoorStateChangeListener> doorStateChangeListeners;

    private static FakeMachine fakeMachine =null;

    public FakeMachine getMachineInstance(){
        if (fakeMachine == null) {
            synchronized (FakeMachine.class) {
                if (fakeMachine == null) {
                    fakeMachine = new FakeMachine();
                }
            }
        }
        return fakeMachine;
    }
    public void addNewCoinInterfaceStatListener(CoinInterFace.CoinInterfaceListener coinInterfaceListener){
        if(coinInterfaceListener!=null){
            this.coinInterfaceListeners.add(coinInterfaceListener);

        }
    }
    public void addDoorStateInterfaceStatListener(DoorState.DoorStateChangeListener doorStateChangeListener){
        if(doorStateChangeListener!=null){
            this.doorStateChangeListeners.add(doorStateChangeListener);

        }
    }

    public void addNewDrinkInterfaceStatListener(CoinInterFace.CoinInterfaceListener coinInterfaceListener){
        if(coinInterfaceListener!=null){
            this.coinInterfaceListeners.add(coinInterfaceListener);
        }
    }
    public FakeMachine() {
        List<Coin> coinList=new ArrayList();
        List<Drink> drinkList=new ArrayList();
        coinInterfaceListeners=new ArrayList<>();

        coinInterFace=new CoinInterFace(this, coinList);
        drinkInterface=new DrinkInterface(this,drinkList);
        doorState=DoorState.getInstance(this);
    }

    @Override
    public void onDrinkDispensed(Drink drink) {
        drinksInterfaceListeners.forEach(drinkInterfaceListener -> {
            drinkInterfaceListener.onDrinkDispensed(drink);
        });
    }
    public  void dispenseDrink(Drink drink){
        drinkInterface.dispenseDrink(drink);
    }

    public void updateDrinkStock(Drink drink,int qty){
       drinkInterface.updateDrinkStock(drink,qty);
    }
    public void updateCoinStock(Coin coin, int qty){
        coinInterFace.updateCoinStock(coin,qty);
    }
    public  void acceptCoin(Coin coin){
        coinInterFace.insertCoin(coin);
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
}
