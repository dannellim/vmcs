package vmcs.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import vmcs.model.Coin;
import vmcs.model.DoorState.DoorStateChangeListener;
import vmcs.model.Drink;
import vmcs.model.Stock;
import vmcs.physical.CoinInterface.CoinInterfaceListener;
import vmcs.physical.DrinkInterface.DrinkInterfaceListener;
import vmcs.physical.MachineFactory;
import vmcs.ui.MachineryPanel;
import vmcs.ui.MachineryPanelImpl;

public class MachineryControllerImpl implements CoinInterfaceListener, DoorStateChangeListener, DrinkInterfaceListener, MachineryController {

    private final MachineryPanel machineryPanel;

    public MachineryControllerImpl(MachineryPanel machineryPanel) {
        MachineFactory.getMachine().addDoorStateInterfaceStatListener(this);
        MachineFactory.getMachine().addNewCoinInterfaceStatListener(this);
        MachineFactory.getMachine().addNewDrinkInterfaceStatListener(this);
        this.machineryPanel = machineryPanel;
    }

    @Override
    public void init() {
        addDrinksToUI();
        addCoinsToUI();
        updateDoorStateToUI();
        updateUIAccordingToDoorState(MachineFactory.getMachine().isDoorLock());
    }

    private void updateDoorStateToUI() {
        machineryPanel.updateDoorLockState(MachineFactory.getMachine().isDoorLock());
    }

    private void addCoinsToUI() {
        Iterator<Coin> iterator = MachineFactory.getMachine().getAllCoins().iterator();
        while (iterator.hasNext()) {
            Coin coin = (Coin) iterator.next();
            machineryPanel.addNewCoins(coin);
        }
        machineryPanel.refresh();
    }

    private void addDrinksToUI() {
        Iterator<Drink> iterator = MachineFactory.getMachine().getAllDrinks().iterator();
        while (iterator.hasNext()) {
            Drink drink = iterator.next();
            machineryPanel.addNewDrink(drink);
        }
        machineryPanel.refresh();
    }

    @Override
    public void changeDrinkStock(Drink drink, int qty) {
        if (!MachineFactory.getMachine().isDoorLock()) {
            if (qty >= 0 && qty <= 20) {
                MachineFactory.getMachine().updateDrinkStock(drink, qty);
            }
        }
    }

    @Override
    public void changeCoinStock(Coin coin, int qty) {
        if (!MachineFactory.getMachine().isDoorLock()) {
            if (qty >= 0 && qty <= 40) {
                MachineFactory.getMachine().updateCoinStock(coin, qty);
            }
        }
    }

    private void updateUIAccordingToDoorState(boolean arg0) {
        machineryPanel.updateDoorLockState(arg0);
        machineryPanel.changeTextFieldState(arg0);
    }

    @Override
    public void lockDoor() {
        MachineFactory.getMachine().lockDoor();
    }

    @Override
    public void unLockDoor() {

        MachineFactory.getMachine().unlockDoor();

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
