package vmcs.controller;

import vmcs.ui.MaintenancePanel;
import vmcs.factory.PropertiesFactory;
import vmcs.model.Coin;
import vmcs.model.Drink;
import vmcs.model.MaintainerState;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import vmcs.physical.MachineImpl;

public class MaintainerControllerImpl implements Observer, MaintainerController {

    private final MaintenancePanel maintenancePanel;
    private final PropertiesFactory propertiesFactory;
    private String PASSWORD = "123";
    private Drink selectedDrink;

    public MaintainerControllerImpl(MaintenancePanel maintenancePanel) {
        propertiesFactory = new PropertiesFactory();
        PASSWORD = propertiesFactory.getProperty(PropertiesFactory.MACHINE)
                .getProperty("password");
        this.maintenancePanel = maintenancePanel;
        setDrinkStocks();
        setCoinStocks();
    }

    @Override
    public void setSelectedDrink(Drink drink) {
        selectedDrink = drink;
    }

    @Override
    public void lock() {
        maintenancePanel.lock();
    }

    @Override
    public void unlock() {
        maintenancePanel.unlock();
    }

    @Override
    public void logIn() {
        MaintainerState.getInstance().setLogIn(true);
    }

    @Override
    public void unLogIn() {
        MaintainerState.getInstance().setLogIn(false);
    }

    @Override
    public void checkPassword(String password) {
        if (password.isEmpty() || password == null) {
            maintenancePanel.resetPassword();
        } else if (validatePassword(password)) {
            maintenancePanel.validPassword();
            MachineImpl.getMachine().unlockDoor();
            MaintainerState.getInstance().setLogIn(true);
        } else {
            maintenancePanel.invalidPassword();
        }
    }

    @Override
    public boolean validatePassword(String password) {
        return password.equals(PASSWORD);
    }

    private void setDrinkStocks() {
        Iterator<Drink> iterator = MachineImpl.getMachine().getAllDrinks().iterator();
        while (iterator.hasNext()) {
            Drink drink = (Drink) iterator.next();
            maintenancePanel.addNewDrink(drink);
        }
        maintenancePanel.refresh();
    }
    
    private void setCoinStocks() {
        Iterator<Coin> iterator = MachineImpl.getMachine().getAllCoins().iterator();
        while (iterator.hasNext()) {
            Coin coin = (Coin) iterator.next();
            maintenancePanel.addNewCoins(coin);
        }
        maintenancePanel.refresh();
    }

    @Override
    public void collectAllCash() {
        MachineImpl.getMachine().getAllCoins().forEach(coin -> {
            coin.setQuantity(0);
        });
    }

    @Override
    public void showTotalCashHeld() {
        int total = 0;
        total = getTotalCash(total);
        maintenancePanel.showTotalCashHeld(total);
    }

    private int getTotalCash(int total) {
        Iterator<Coin> iterator = MachineImpl.getMachine().getAllCoins().iterator();
        while (iterator.hasNext()) {
            Coin coin = (Coin) iterator.next();
            if (!coin.getName().equalsIgnoreCase("Invalid")) {
                int amount = coin.getValue();
                int quantity = coin.getQuantity();
                total += amount * quantity;
            }
        }
        return total;
    }

    @Override
    public void changePrice(String newValue) {
        //System.out.println("change price" + newValue);
        if (selectedDrink != null) {
            int price = Integer.parseInt(newValue);
            selectedDrink.setValue(price);
            MachineImpl.getMachine().updateDrinkPrice(selectedDrink, price);
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        //do full panel refresh here
        System.out.println("---------------------- MaintainerController UPDATE Start ----------------------");
        if (arg0 instanceof Coin) {
            System.out.println("Coin Update");
            Iterator<Coin> iterator = MachineImpl.getMachine().getAllCoins().iterator();
            while (iterator.hasNext()) {
                Coin coin = (Coin) iterator.next();
                System.out.println(coin.toString());
            }
        } else if (arg0 instanceof Drink) {
            System.out.println("Drink Update");
            Iterator<Drink> iterator = MachineImpl.getMachine().getAllDrinks().iterator();
            while (iterator.hasNext()) {
                Drink drink = (Drink) iterator.next();
                System.out.println(drink.toString());
            }
        }
        System.out.println("---------------------- MaintainerController UPDATE End ----------------------");
    }

    @Override
    public boolean isLock() {
        return MachineImpl.getMachine().isDoorLock();
    }
}
