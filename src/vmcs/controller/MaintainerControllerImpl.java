package vmcs.controller;


import vendingmachinecontrolsystem.ui.MaintenancePanel;
import vmcs.factory.PropertiesFactory;
import vmcs.model.Coin;
import vmcs.model.Drink;
import vmcs.model.MaintainerState;
import vmcs.model.Stock;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MaintainerControllerImpl implements Observer, MaintainerController {
	
	private static volatile MaintainerControllerImpl maintainerControllerImpl;

	private List<Stock> COIN_STOCKS;
	private List<Stock> DRINK_STOCKS;
	private MaintenancePanel maintenancePanel;
    private PropertiesFactory propertiesFactory;
    private String PASSWORD="123";
    private MaintainerState maintainerState;
    private Drink selectedDrink;

	public static MaintainerControllerImpl get() {
		if (maintainerControllerImpl == null) {
			synchronized (MaintainerControllerImpl.class) {
				if (maintainerControllerImpl == null) {
					maintainerControllerImpl = new MaintainerControllerImpl();
				}
			}
		}
		return maintainerControllerImpl;
	}

	private MaintainerControllerImpl() {
		if (maintainerControllerImpl != null) {
			throw new RuntimeException("Use get() method to get the single instance of this class.");
		}
		propertiesFactory = new PropertiesFactory();
    	PASSWORD = propertiesFactory.getProperty(PropertiesFactory.MACHINE)
    			.getProperty("password");
		maintenancePanel = new MaintenancePanel();
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
	public void showPanel() {
		if (maintenancePanel != null)
			maintenancePanel.setVisible(true);
	}
	
	@Override
	public void closePanel() {
		if (maintenancePanel != null)
			maintenancePanel.setVisible(false);
	}
	
	@Override
	public void logIn(){
		maintainerState.setLogIn(true);
	}
	@Override
	public void unLogIn(){
		maintainerState.setLogIn(false);
	}
	
    @Override
	public void checkPassword(String password) {
        if(password.isEmpty() || password == null){
        	maintenancePanel.resetPassword();  
        } else if (validatePassword(password)) {
        	maintenancePanel. validPassword();
        	MaintainerState.getInstance().setLogIn(true);
        } else {
        	maintenancePanel.invalidPassword();
        }
    }
    
    @Override
	public boolean validatePassword(String password) {
    	return password.equals(PASSWORD);
    }
	
	@Override
	public void setDrinkStocks(List<Stock> drinkList) {
		DRINK_STOCKS = drinkList;
		Iterator<Stock> iterator = DRINK_STOCKS.iterator();
		while (iterator.hasNext()) {
			Drink drink = (Drink) iterator.next();
			maintenancePanel.addNewDrink(drink);
		}
		maintenancePanel.pack();
		maintenancePanel.setLocationRelativeTo(null);
	}
	
	@Override
	public void setCoinStocks(List<Stock> coinList) {
		COIN_STOCKS = coinList;
		Iterator<Stock> iterator = COIN_STOCKS.iterator();
		while (iterator.hasNext()) {
			Coin coin = (Coin) iterator.next();
			maintenancePanel.addNewCoins(coin);
		}
		maintenancePanel.pack();
		maintenancePanel.setLocationRelativeTo(null);
	}

	@Override
	public void collectAllCash(){
		COIN_STOCKS.forEach(coin->{
			coin.setQuantity(0);
		});
	}
	
	@Override
	public void showTotalCashHeld() {
		double total = 0;
		total = getTotalCash(total);
		maintenancePanel.showTotalCashHeld(total);
	}

	@Override
	public double getTotalCash(double total) {
		Iterator<Stock> iterator = COIN_STOCKS.iterator();
		while(iterator.hasNext()) {
			Coin coin = (Coin) iterator.next();
            if (!coin.getName().equalsIgnoreCase("Invalid")) {
    			double amount = coin.getValue();
                int quantity = coin.getQuantity();
                total += amount * quantity;
            }
		}
		return total;
	}
	
	@Override
	public void changePrice(String newValue) {
    	//System.out.println("change price" + newValue);
    	if(selectedDrink != null) {
    		double price = Double.parseDouble(newValue);
    		selectedDrink.setValue(price);
    	}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		//do full panel refresh here
		System.out.println("---------------------- MaintainerController UPDATE Start ----------------------");
		if (arg0 instanceof Coin) {
			System.out.println("Coin Update");
			Iterator<Stock> iterator = COIN_STOCKS.iterator();
			while (iterator.hasNext()) {
				Coin coin = (Coin) iterator.next();
				System.out.println(coin.toString());
			}
		} else if (arg0 instanceof Drink) {
			System.out.println("Drink Update");
			Iterator<Stock> iterator = DRINK_STOCKS.iterator();
			while (iterator.hasNext()) {
				Drink drink = (Drink) iterator.next();
				System.out.println(drink.toString());
			}
		}
		System.out.println("---------------------- MaintainerController UPDATE End ----------------------");
	}

}
