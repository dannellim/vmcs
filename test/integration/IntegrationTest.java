/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import vmcs.ui.CustomerPanelImpl;
import vmcs.ui.SimulatorControlPanel;
import vmcs.ui.SimulatorControlPanelImpl;

/**
 *
 * @author Dannel
 */
public class IntegrationTest {

    SimulatorControlPanel simulator = new SimulatorControlPanelImpl();

    @Before
    public void setUp() {
        simulator = new SimulatorControlPanelImpl();
        simulator.show();
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void test(){
        simulator.beginSim();
        simulator.startCust();
        CustomerPanelImpl.getInstance().updateInsertedAmount(5);
        String result = CustomerPanelImpl.getInstance().getInsertedAmount();
        assertEquals(result, "5 c");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
