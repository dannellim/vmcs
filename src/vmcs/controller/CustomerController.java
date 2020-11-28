/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.controller;

import java.util.List;
import vmcs.model.Coin;
import vmcs.model.Drink;
import vmcs.ui.CustomerPanel;


/**
 *
 * @author Dannel
 */
public abstract class CustomerController {
    
    protected CustomerPanel customerPanel;
    
    public CustomerController(CustomerPanel customerPanel){
        this.customerPanel = customerPanel;
    }
    
    public abstract List<Coin> getCoinStocks();
    
    public abstract void insertCoin(Coin coin);
    
    public abstract List<Drink> getDrinkStocks();
    
    public abstract void selectDrink(Drink drink);
    
    public abstract void terminateTransaction();
}
