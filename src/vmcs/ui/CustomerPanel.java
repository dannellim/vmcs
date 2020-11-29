/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.ui;

import java.util.List;
import vmcs.controller.CustomerController;
import vmcs.model.Coin;
import vmcs.model.Drink;

/**
 *
 * @author Dannel
 */
public interface CustomerPanel {
    
    public void init();

    public void updateInsertedAmount(int amount);

    public void displayInvalidCoin();

    public void disableCoinButtons();

    public void disableTransactions();

    public void enableTransactions();

    public void refreshDrinkPanel(List<Drink> drinkList);

    public String getInsertedAmount();

    public void dispenseDrink(Drink drink);
    
    public void dispenseChange(Coin coin);
    
    public void dispenseChange(List<Coin> coin);

    public void displayChange(int originalChange, int changeAvailable);

    public void resetState();

    public void enableTerminateButton();

    public void terminateTransaction();

    public void show();

    public void hide();

    public void refresh();

}
