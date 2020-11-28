/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.controller;

import vmcs.ui.CustomerPanel;

/**
 *
 * @author Dannel
 */
public abstract class CustomerController {
    
    protected CustomerPanel customerPanel;
    
    public abstract void init();
    
    public abstract void close();
}
