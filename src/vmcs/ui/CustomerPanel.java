/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.ui;

import java.util.List;
import vmcs.model.Coin;
import vmcs.model.Drink;

/**
 *
 * @author Dannel
 */
public abstract class CustomerPanel {

    public abstract void addNewCoins(Coin coin);

    public abstract void updateInsertedAmount(double amount);

    public abstract void displayInvalidCoin();

    public abstract void enableCoinButtons();

    public abstract void disableCoinButtons();

    public abstract void addNewDrink(Drink drink);

    public abstract void setSelectedDrink(Drink drink);

    public abstract void disableTransactions();

    public abstract void enableTransactions();

    public abstract void refreshDrinkPanel(List<Drink> drinkList);

    public abstract String getInsertedAmount();

    public abstract void dispenseDrink(Drink drink);

    public abstract void displayChange(double originalChange, double changeAvailable);

    public abstract void resetState();

    public abstract void enableTerminateButton();

    public abstract void disableTerminateButton();

    public abstract void terminateTransaction();

    public abstract void show();

    public abstract void hide();

    public abstract void refresh();

}
