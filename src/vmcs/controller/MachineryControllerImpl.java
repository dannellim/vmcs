package vmcs.controller;

import java.util.Iterator;
import java.util.List;
import vmcs.model.Coin;
import vmcs.model.DoorState.DoorStateChangeListener;
import vmcs.model.Drink;
import vmcs.model.MaintainerState;
import vmcs.physical.CoinInterface.CoinInterfaceListener;
import vmcs.physical.DrinkInterface.DrinkInterfaceListener;
import vmcs.physical.MachineImpl;
import vmcs.ui.MachineryPanel;

public class MachineryControllerImpl implements CoinInterfaceListener, DoorStateChangeListener, DrinkInterfaceListener, MachineryController {

    private final MachineryPanel machineryPanel;

    public MachineryControllerImpl(MachineryPanel machineryPanel) {
        MachineImpl.getMachine().addDoorStateInterfaceStatListener(this);
        MachineImpl.getMachine().addNewCoinInterfaceStatListener(this);
        MachineImpl.getMachine().addNewDrinkInterfaceStatListener(this);
        this.machineryPanel = machineryPanel;
    }

    @Override
    public void init() {
        addDrinksToUI();
        addCoinsToUI();
        updateDoorStateToUI();
        updateUIAccordingToDoorState(MachineImpl.getMachine().isDoorLock());
    }

    private void updateDoorStateToUI() {
        machineryPanel.updateDoorLockState(MachineImpl.getMachine().isDoorLock());
    }

    private void addCoinsToUI() {
        Iterator<Coin> iterator = MachineImpl.getMachine().getAllCoins().iterator();
        while (iterator.hasNext()) {
            Coin coin = (Coin) iterator.next();
            machineryPanel.addCoinToUI(coin);
        }
        machineryPanel.refresh();
    }

    private void addDrinksToUI() {
        Iterator<Drink> iterator = MachineImpl.getMachine().getAllDrinks().iterator();
        while (iterator.hasNext()) {
            Drink drink = iterator.next();
            machineryPanel.addDrinkToUI(drink);
        }
        machineryPanel.refresh();
    }

    @Override
    public void changeDrinkStock(Drink drink, int qty) {
        if (!MachineImpl.getMachine().isDoorLock()) {
            if (qty >= 0 && qty <= 20) {
                MachineImpl.getMachine().updateDrinkStock(drink, qty);
            }
        }
    }

    @Override
    public void changeCoinStock(Coin coin, int qty) {
        if (!MachineImpl.getMachine().isDoorLock()) {
            if (qty >= 0 && qty <= 40) {
                MachineImpl.getMachine().updateCoinStock(coin, qty);
            }
        }
    }

    private void updateUIAccordingToDoorState(boolean arg0) {
        machineryPanel.updateDoorLockState(arg0);
        machineryPanel.changeTextFieldState(arg0);
    }

    @Override
    public void lockDoor() {
            MachineImpl.getMachine().lockDoor();

    }

    @Override
    public void unLockDoor() {

        if(MaintainerState.getInstance().isLogIn()){
            MachineImpl.getMachine().unlockDoor();
        }else{
            MachineImpl.getMachine().lockDoor();

        }

    }

    @Override
    public void onCoinAccepted(Coin coin) {
    }

    @Override
    public void onCoinRejected(Coin coin) {
    }

    @Override
    public void onCoinDispensed(Coin coin) {
    }

    @Override
    public void onCoinDispensed(List<Coin> coin) {
    }

    @Override
    public void onCoinStockChanged(Coin coin) {
        machineryPanel.updateCoinUI(coin);

    }

    @Override
    public void onDoorStateChange(boolean isLock) {
        System.out.println("Islocked "+isLock);
        updateUIAccordingToDoorState(isLock);
    }

    @Override
    public void onDrinkDispensed(Drink drink) {
    }

    @Override
    public void onDrinkStockChanged(Drink drink) {
        machineryPanel.updateDrinkUI(drink);
    }

}
