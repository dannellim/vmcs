/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import vmcs.memento.TransactionCaretaker;
import vmcs.memento.TransactionOriginator;
import vmcs.model.Coin;
import vmcs.model.Drink;
import vmcs.physical.CoinInterface.CoinInterfaceListener;
import vmcs.physical.DrinkInterface.DrinkInterfaceListener;
import vmcs.physical.MachineImpl;
import vmcs.ui.CustomerPanel;
import vmcs.util.CurrencyHelper;

/**
 *
 * @author Dannel
 */
public class CustomerControllerImpl implements CustomerController, CoinInterfaceListener, DrinkInterfaceListener {

    private TransactionOriginator transactionCoinOriginator;
    private TransactionCaretaker transactionCoinCaretaker;
    private Drink selectedDrink;

    private final CustomerPanel customerPanel;

    public CustomerControllerImpl(CustomerPanel customerPanel) {
        MachineImpl.getMachine().addNewCoinInterfaceStatListener(this);
        MachineImpl.getMachine().addNewDrinkInterfaceStatListener(this);
        this.customerPanel = customerPanel;
    }

    @Override
    public List<Coin> getCoinStocks() {
        return MachineImpl.getMachine().getAllCoins();
    }

    @Override
    public void insertCoin(Coin coin) {
        MachineImpl.getMachine().acceptCoin(coin);
    }

    @Override
    public List<Drink> getDrinkStocks() {
        return MachineImpl.getMachine().getAllDrinks();
    }

    @Override
    public void selectDrink(Drink drink) {
        selectedDrink = drink;
        customerPanel.enableTerminateButton();
        transactionCoinOriginator = new TransactionOriginator();
        transactionCoinCaretaker = new TransactionCaretaker();
    }

    @Override
    public void terminateTransaction() {
        restoreCoinStockAftTermination();
        customerPanel.refreshDrinkPanel(MachineImpl.getMachine().getAllDrinks());
    }

    private void restoreCoinStockAftTermination() {
        if (transactionCoinCaretaker != null) {
            List<Coin> coins = new ArrayList();
            for (int i = 0; i < transactionCoinCaretaker.size(); i++) {
                transactionCoinOriginator.getStateFromMemento(transactionCoinCaretaker.get(i));
                Coin coin = (Coin) transactionCoinOriginator.getStock();
                coins.add(coin);
            }
            MachineImpl.getMachine().dispense(coins);
        }
    }

    @Override
    public void onCoinAccepted(Coin coin) {
        if (transactionCoinOriginator != null) {
            customerPanel.updateInsertedAmount(coin.getValue());
            transactionCoinOriginator.setStock(coin);
            transactionCoinCaretaker.addMemento(transactionCoinOriginator.saveStateToMemento());
            checkAmountSufficiency();
        }
    }

    @Override
    public void onCoinRejected(Coin coin) {
        customerPanel.displayInvalidCoin();
    }

    @Override
    public void onCoinDispensed(Coin coin) {
        customerPanel.dispenseChange(coin);
    }

    @Override
    public void onCoinDispensed(List<Coin> coin) {
        customerPanel.dispenseChange(coin);
    }

    @Override
    public void onCoinStockChanged(Coin coin) {
        //System.out.println(coin.toString());
    }

    private void checkAmountSufficiency() {
        int amount = CurrencyHelper.coinsToAmount(customerPanel.getInsertedAmount());
        if (amount >= selectedDrink.getValue()) {
            MachineImpl.getMachine().dispenseDrink(selectedDrink);
            calculateChange(amount, selectedDrink.getValue());
        }
    }

    private void calculateChange(int amount, int drinkValue) {
        int change = amount - drinkValue;
        int highestChangeAvailable = calculateHighestChange(change, MachineImpl.getMachine().getAllCoins());
        customerPanel.displayChange(change, highestChangeAvailable);
    }

    private int calculateHighestChange(int change, List<Coin> coins) {
        int result = 0;
        int remainder = change;
        int lowestCoinDenomination = 0;
        for (int i = 0; i < coins.size(); i++) {
            Coin coin = coins.get(i);
            if (coin.getQuantity() > 0) {
                if (i == 0 || lowestCoinDenomination == 0) {
                    lowestCoinDenomination = coin.getValue();
                } else if (coin.getValue() < lowestCoinDenomination) {
                    lowestCoinDenomination = coin.getValue();
                }
            }
        }
        for (int i = 0; i < coins.size(); i++) {
            if (remainder == 0 | remainder < lowestCoinDenomination) {
                break; // no coin available to subtract and return
            }
            Coin coin = coins.get(i);
            if (coin.getQuantity() > 0) {
                if (coin.getValue() <= remainder) {
                    if (!checkIfHigerCoinAvailable(remainder, coin.getValue(), MachineImpl.getMachine().getAllCoins())) {
                        remainder = remainder - coin.getValue();
                        result = result + coin.getValue();
                        coin.setQuantity(coin.getQuantity() - 1); // must subtract to return to customer
                    }
                }
            }
            if (i == coins.size() - 1) {
                i = -1; // restart loop
            }
        }
        return result;
    }

    private boolean checkIfHigerCoinAvailable(int amount, int coinValueCheck, List<Coin> coins) {
        boolean result = false;
        Iterator<Coin> iterator = coins.iterator();
        while (iterator.hasNext()) {
            Coin coin = iterator.next();
            if (coin.getValue() >= amount && amount % coin.getValue() == 0) {
                if (coin.getValue() > coinValueCheck) {
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public void onDrinkDispensed(Drink drink) {
        System.out.println(drink.toString());
        customerPanel.dispenseDrink(drink);
        new Timer().schedule(new TimerTask() {
            public void run() {
                customerPanel.resetState();
            }
        }, 2500);
    }

    @Override
    public void onDrinkStockChanged(Drink drink) {
        customerPanel.refreshDrinkPanel(MachineImpl.getMachine().getAllDrinks());
    }
}
