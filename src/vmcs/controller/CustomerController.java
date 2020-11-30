/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.controller;

import java.util.List;
import vmcs.model.Coin;
import vmcs.model.Drink;


/**
 *
 * @author Dannel
 */
public interface CustomerController {
    
    public List<Coin> getCoinStocks();
    
    public void insertCoin(Coin coin);
    
    public List<Drink> getDrinkStocks();
    
    public void selectDrink(Drink drink);
    
    public Drink getSelectedDrink();
    
    public void terminateTransaction();
    
    public void setFault();
}
