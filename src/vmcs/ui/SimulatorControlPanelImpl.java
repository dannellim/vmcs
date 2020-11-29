/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.ui;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import vmcs.factory.PropertiesAPI;
import vmcs.factory.PropertiesFactory;
import vmcs.model.Coin;
import vmcs.model.Drink;
import vmcs.physical.MachineImpl;
import vmcs.util.CurrencyHelper;

/**
 *
 * @author Dannel
 */
public class SimulatorControlPanelImpl extends SimulatorControlPanel {

    /**
     * Creates new form SimulatorControlPanel
     */
    private PropertiesFactory propertiesFactory;

    public SimulatorControlPanelImpl() {
        initComponents();
        disableSimulator();
        propertiesFactory = new PropertiesFactory();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

	jFrame = new javax.swing.JFrame();
        machButton = new javax.swing.JButton();
        mainButton = new javax.swing.JButton();
        custButton = new javax.swing.JButton();
        endSimButton = new javax.swing.JButton();
        beginSimButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jFrame.setTitle("VMCS - Simulator Control Panel");
        jFrame.setResizable(false);

        machButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        machButton.setText("Activate Machinery Panel");
        machButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                machButtonActionPerformed(evt);
            }
        });

        mainButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mainButton.setText("Activate Maintainer Panel");
        mainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainButtonActionPerformed(evt);
            }
        });

        custButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        custButton.setText("Activate Customer Panel");
        custButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custButtonActionPerformed(evt);
            }
        });

        endSimButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        endSimButton.setText("End Simulator");
        endSimButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endSimButtonActionPerformed(evt);
            }
        });

        beginSimButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        beginSimButton.setText("Begin Simulator");
        beginSimButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beginSimButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Simulator Control Panel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jFrame.getContentPane());
        jFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(mainButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(machButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(custButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endSimButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(beginSimButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beginSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(endSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(custButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(machButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void machButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_machButtonActionPerformed
        // TODO add your handling code here:
        startMach();
    }//GEN-LAST:event_machButtonActionPerformed

    private void mainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainButtonActionPerformed
        // TODO add your handling code here:
        startMain();
    }//GEN-LAST:event_mainButtonActionPerformed

    private void custButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custButtonActionPerformed
        // TODO add your handling code here:
        startCust();
    }//GEN-LAST:event_custButtonActionPerformed

    private void endSimButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endSimButtonActionPerformed
        // TODO add your handling code here:
        endSim();
    }//GEN-LAST:event_endSimButtonActionPerformed

    private void beginSimButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beginSimButtonActionPerformed
        // TODO add your handling code here:
        beginSim();
    }//GEN-LAST:event_beginSimButtonActionPerformed

    private void enableSimulator() {
        custButton.setEnabled(true);
        endSimButton.setEnabled(true);
        machButton.setEnabled(true);
        mainButton.setEnabled(true);
        beginSimButton.setEnabled(false);
    }

    private void disableSimulator() {
        custButton.setEnabled(false);
        endSimButton.setEnabled(false);
        machButton.setEnabled(false);
        mainButton.setEnabled(false);
        beginSimButton.setEnabled(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton beginSimButton;
    private javax.swing.JButton custButton;
    private javax.swing.JButton endSimButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton machButton;
    private javax.swing.JButton mainButton;
    private javax.swing.JFrame jFrame;
    // End of variables declaration//GEN-END:variables

    @Override
    public void startMach() {
        MachineryPanelImpl.getInstance().refresh();
        MachineryPanelImpl.getInstance().show();
    }

    @Override
    public void startMain() {
        MaintenancePanelImpl.getInstance().init();
        MaintenancePanelImpl.getInstance().show();
    }

    @Override
    public void startCust() {
        CustomerPanelImpl.getInstance().show();
    }

    @Override
    public void beginSim() {
        enableSimulator();
        MachineImpl.getMachine().initStocks(initCoins(), initDrinks());
    }

    @Override
    public void show() {
        jFrame.setVisible(true);
    }

    @Override
    public void endSim() {
        CustomerPanelImpl.getInstance().hide();
        MachineryPanelImpl.getInstance().hide();
        disableSimulator();
        saveProperties();
    }

    private List<Coin> initCoins() {
        List<Coin> coinStocks = new ArrayList<>();
        Properties coinProperties = propertiesFactory.getProperty(PropertiesFactory.COIN);
        Enumeration<String> enums = (Enumeration<String>) coinProperties.propertyNames();
        while (enums.hasMoreElements()) {
            Coin coin = getCoinFromPropertiesValue(coinProperties, enums);
            coinStocks.add(coin);
        }
        return coinStocks;
    }

    private Coin getCoinFromPropertiesValue(Properties coinProperties, Enumeration<String> enums) {
        String key = enums.nextElement();
        String value = coinProperties.getProperty(key);
        String[] data = value.split(PropertiesAPI.SEPERATOR);
        String price = data[0];
        String quantity = data[1];
        Coin coin = new Coin();
        coin.setName(key);
        coin.setValue(Integer.parseInt(price));
        coin.setQuantity(Integer.parseInt(quantity));
        System.out.println(coin.toString());
        return coin;
    }

    private List<Drink> initDrinks() {
        List<Drink> drinkStocks = new ArrayList<>();
        Properties drinkProperties = propertiesFactory.getProperty(PropertiesFactory.DRINK);
        Enumeration<String> enums = (Enumeration<String>) drinkProperties.propertyNames();
        while (enums.hasMoreElements()) {
            Drink drink = getDrinkFromPropertiesValue(drinkProperties, enums);
            drinkStocks.add(drink);
        }
        return drinkStocks;
    }

    private Drink getDrinkFromPropertiesValue(Properties drinkProperties, Enumeration<String> enums) {
        String key = enums.nextElement();
        String value = drinkProperties.getProperty(key);
        String[] data = value.split(PropertiesAPI.SEPERATOR);
        String price = data[0];
        String quantity = data[1];
        Drink drink = new Drink();
        drink.setName(key.replace("_", "").toUpperCase());
        drink.setValue(CurrencyHelper.coinsToAmount(price));
        drink.setQuantity(Integer.parseInt(quantity));
        System.out.println(drink);
        return drink;
    }

    private void saveProperties() {
        if (propertiesFactory != null) {
            Properties coinProp = new Properties();
            List<Coin> coins = MachineImpl.getMachine().getAllCoins();
            for(Coin coin: coins){
                coinProp.put(coin.getName(), coin.getValue() + ";" + coin.getQuantity());
            }
            propertiesFactory.saveProperties(PropertiesFactory.COIN, coinProp);
            System.out.println(coinProp);
            Properties drinkProp = new Properties();
            List<Drink> drinks = MachineImpl.getMachine().getAllDrinks();
            for (Drink drink : drinks) {
                drinkProp.put(drink.getName(), CurrencyHelper.toCoins(drink.getValue()) + ";" + drink.getQuantity());
            }
            propertiesFactory.saveProperties(PropertiesFactory.DRINK, drinkProp);
            System.out.println(drinkProp);
        }
    }
}
