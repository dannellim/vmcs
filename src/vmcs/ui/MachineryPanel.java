/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.ui;

import vmcs.model.Coin;
import vmcs.model.Drink;

/**
 *
 * @author Dannel
 */
public interface MachineryPanel {

    void addNewCoins(Coin coin);

    void addNewDrink(final Drink drink);

    void changeTextFieldState(boolean state);

    void updateCoinUI(Coin coin);

    void updateDoorLockState(boolean isLocked);

    void updateDrinkUI(Drink drink);
    
    void show();
    
    void hide();
    
    void refresh();
    
}
