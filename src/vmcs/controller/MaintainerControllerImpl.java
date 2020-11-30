package vmcs.controller;

import vmcs.ui.MaintenancePanel;
import vmcs.factory.PropertiesFactory;
import vmcs.model.Coin;
import vmcs.model.Drink;
import vmcs.model.MaintainerState;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import vmcs.physical.CoinInterface;
import vmcs.physical.DrinkInterface;
import vmcs.physical.MachineImpl;

public class MaintainerControllerImpl implements Observer, MaintainerController, CoinInterface.CoinInterfaceListener, DrinkInterface.DrinkInterfaceListener {

    private final MaintenancePanel maintenancePanel;
    private final PropertiesFactory propertiesFactory;
    private String PASSWORD = "123";
    private Drink selectedDrink;

    public MaintainerControllerImpl(MaintenancePanel maintenancePanel) {
        MachineImpl.getMachine().addNewCoinInterfaceStatListener(this);
        MachineImpl.getMachine().addNewDrinkInterfaceStatListener(this);
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
    public Drink getSelectedDrink() {
        return this.selectedDrink;
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
        maintenancePanel.refreshCoins(MachineImpl.getMachine().getAllCoins());
        maintenancePanel.refreshDrinks(MachineImpl.getMachine().getAllDrinks());
        MachineImpl.getMachine().unlockDoor();
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
            logIn();
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
    public int showTotalCashHeld() {
        int total = 0;
        total = getTotalCash(total);
        maintenancePanel.showTotalCashHeld(total);
        return total;
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
            List<Coin> coins = MachineImpl.getMachine().getAllCoins();
            Iterator<Coin> iterator = coins.iterator();
            while (iterator.hasNext()) {
                Coin coin = (Coin) iterator.next();
                System.out.println(coin.toString());
            }
        } else if (arg0 instanceof Drink) {
            System.out.println("Drink Update");
            List<Drink> drinks = MachineImpl.getMachine().getAllDrinks();
            Iterator<Drink> iterator = drinks.iterator();
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

    @Override
    public void onCoinAccepted(Coin coin) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onCoinRejected(Coin coin) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onCoinDispensed(Coin coin) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onCoinDispensed(List<Coin> coin) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onCoinStockChanged(Coin coin) {
        System.out.println(coin.toString());
        List<Coin> coins = MachineImpl.getMachine().getAllCoins();
        maintenancePanel.refreshCoins(coins);
    }

    @Override
    public void onDrinkDispensed(Drink drink) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDrinkStockChanged(Drink drink) {
        System.out.println(drink.toString());
        List<Drink> drinks = MachineImpl.getMachine().getAllDrinks();
        maintenancePanel.refreshDrinks(drinks);
    }
}
