/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.controller;

import java.util.List;
import vmcs.model.Coin;
import vmcs.physical.MachineFactory;
import vmcs.ui.CustomerPanelImpl;

/**
 *
 * @author Dannel
 */
public class CustomerControllerImpl extends CustomerController {

    @Override
    public void init() {
        customerPanel = new CustomerPanelImpl();
        List<Coin> coins = MachineFactory.getMachine().getAllCoins();
        for(Coin coin : coins){
            customerPanel.addNewCoins(coin);
        }
    }

    @Override
    public void close() {
        if(customerPanel!=null)
            customerPanel.hide();
    }
    
}
