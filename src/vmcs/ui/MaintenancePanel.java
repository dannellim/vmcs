/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.ui;

import java.util.List;
import vmcs.controller.MaintainerController;
import vmcs.model.Coin;
import vmcs.model.Drink;

/**
 *
 * @author Dannel
 */
public interface MaintenancePanel {

    void addNewCoins(Coin coin);

    void addNewDrink(Drink drink);

    void invalidPassword();

    void lock();

    void resetPassword();

    void showTotalCashHeld(int total);

    void unlock();

    void validPassword();
    
    void show();
    
    void hide();
    
    void refresh();
    
    MaintainerController getController();
    
    void refreshDrinks(List<Drink> drinks);
    
    void refreshCoins(List<Coin> coins);
    
}
