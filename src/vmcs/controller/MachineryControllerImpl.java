package vmcs.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import vmcs.model.Coin;
import vmcs.model.Drink;
import vmcs.model.Stock;
import vmcs.physical.MachineFactory;
import vmcs.ui.MachineryPanel;
import vmcs.ui.MachineryPanelImpl;

public class MachineryControllerImpl implements Observer {

    private MachineryPanel machineryPanel;

    private boolean currentDoorSate;

    public void showPanel() {
        if (machineryPanel != null) {
            machineryPanel.show();
        } else {
            machineryPanel = new MachineryPanelImpl();

            addDrinksToUI();
            addCoinsToUI();
            updateDoorStateToUI();
            updateUIAccordingToDoorState(doorState);

        }
    }

    public void closePanel() {
        if (machineryPanel != null) {
            machineryPanel.hide();
        }

    }

    private void updateDoorStateToUI() {
        machineryPanel.updateDoorLockState(doorState.isLocked());
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

    public void changeDrinkStock(Drink drink, int qty) {
        if (!doorState.isLocked()) {
            if (qty >= 0 && qty <= 20) {
                drink.setQuantity(qty);
            }
        }
    }

    public void changeCoinStock(Coin coin, int qty) {
        if (!doorState.isLocked()) {
            if (qty >= 0 && qty <= 40) {
                coin.setQuantity(qty);
            }
        }
    }

    public void setDoorState(DoorState doorState) {
        this.doorState = doorState;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        //do full panel refresh here
        System.out.println("---------------------- MachineryController UPDATE Start ----------------------");
        if (arg0 instanceof Coin) {
            System.out.println("Coin Update");

            if (machineryPanel != null) {
                System.out.println("Coin Update");

                Coin coin = (Coin) arg0;
                machineryPanel.updateCoinUI(coin);
            }

        } else if (arg0 instanceof Drink) {
            System.out.println("Drink MachineryController  Update");

            if (machineryPanel != null) {

                Drink drink = (Drink) arg0;
                machineryPanel.updateDrinkUI(drink);
                System.out.println(arg0.toString());
            }

        } else if (arg0 instanceof DoorState) {
            if (machineryPanel != null) {
                updateUIAccordingToDoorState((DoorState) arg0);
            }

        }
        System.out.println("---------------------- MachineryController UPDATE End ----------------------");
    }

    private void updateUIAccordingToDoorState(DoorState arg0) {
        machineryPanel.updateDoorLockState(arg0.isLocked());
        machineryPanel.changeTextFieldState(arg0.isLocked());
    }

    public void lockDoor() {
        if (MaintainerState.getInstance().isLogIn()) {
            doorState.setLocked(true);
        }
    }

    public void unLockDoor() {

        if (MaintainerState.getInstance().isLogIn()) {
            doorState.setLocked(false);
        }
    }

}
