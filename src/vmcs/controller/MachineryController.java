/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.controller;

import vmcs.model.Coin;
import vmcs.model.Drink;

/**
 *
 * @author Dannel
 */
public interface MachineryController {

    void changeCoinStock(Coin coin, int qty);

    void changeDrinkStock(Drink drink, int qty);

    void init();

    void lockDoor();

    void unLockDoor();
    
}
