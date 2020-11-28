/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.ui;

/**
 *
 * @author Dannel
 */
public abstract class SimulatorControlPanel {
    public abstract void startMach();      

    public abstract void startMain();

    public abstract void startCust();                                   

    public abstract void startSim();    

    public abstract void beginSim();
    
    public abstract void show();
}
