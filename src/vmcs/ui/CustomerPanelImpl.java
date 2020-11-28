/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import vmcs.model.Coin;
import vmcs.model.Drink;
import vmcs.physical.MachineFactory;
import vmcs.util.CurrencyHelper;

/**
 *
 * @author Dannel
 */
public class CustomerPanelImpl extends CustomerPanel {

    /**
     * Creates new form CustomerPanel
     */
    private static final String NOT_IN_STOCK_TEXT = "Not in Stock";
    private static final String NO_COINS_TEXT = "0 c";
    private static final String NO_CAN_TEXT = "NO CAN";
    private static final String INVALID_COIN_TEXT = "Invalid Coin";
    private static final String INSUFFICIENT_CHANGE_TEXT = "Insufficient Change Available";

    public CustomerPanelImpl() {
        initComponents();
    }
   
    @Override
    public void addNewCoins(Coin coin) {
        JButton jButton = new JButton();
        jButton.setFont(new java.awt.Font("Tahoma", 0, 14));
        jButton.setText(coin.getName());
        jButton.setEnabled(false);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });
        coinPanel.add(jButton);
    }

    public void updateInsertedAmount(double amount) {
        amount = CurrencyHelper.add(amount, CurrencyHelper.coinsToAmount(insertedAmountTf.getText()));
        insertedAmountTf.setText(CurrencyHelper.toCoins(amount));
    }

    public void displayInvalidCoin() {
        invalidCoinLabel.setText(INVALID_COIN_TEXT);
    }

    public void enableCoinButtons() {
        Component[] comp = coinPanel.getComponents();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i] instanceof JButton) {
                ((JButton) comp[i]).setEnabled(true);
            }
        }
        comp = drinkPanel.getComponents();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i] instanceof JPanel) {
                Component[] drinkComp = ((JPanel) comp[i]).getComponents();
                for (int j = 0; j < drinkComp.length; j++) {
                    if (drinkComp[j] instanceof JButton) {
                        ((JButton) drinkComp[j]).setEnabled(false);
                    }
                }
            }
        }
    }

    @Override
    public void disableCoinButtons() {
        Component[] comp = coinPanel.getComponents();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i] instanceof JButton) {
                ((JButton) comp[i]).setEnabled(false);
            }
        }
        comp = drinkPanel.getComponents();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i] instanceof JPanel) {
                Component[] drinkComp = ((JPanel) comp[i]).getComponents();
                for (int j = 0; j < drinkComp.length; j++) {
                    if (drinkComp[j] instanceof JButton) {
                        JButton jButton = ((JButton) drinkComp[j]);
                        //jButton.setEnabled(CustomerControllerImpl.get().isDrinkAvailable(jButton.getText()));
                    }
                }
            }
        }
    }

    @Override
    public void addNewDrink(Drink drink) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout());

        JButton jButton = new JButton();
        jButton.setFont(new java.awt.Font("Tahoma", 0, 14));
        jButton.setText(drink.getName());
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //CustomerControllerImpl.get().startTransaction(drink);
            }
        });

        JTextField jTextField = new JTextField();
        jTextField.setText(CurrencyHelper.toCoins(drink.getValue()));
        jTextField.setEditable(false);
        jTextField.setBackground(new java.awt.Color(0, 0, 0));
        jTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField.setForeground(new java.awt.Color(255, 255, 255));
        jTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        JLabel jLabel = new JLabel();
        jLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel.setForeground(java.awt.Color.red);
        jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        if (drink.getQuantity() == 0) {
            jLabel.setText(NOT_IN_STOCK_TEXT);
            jLabel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.red));
            jButton.setEnabled(false);
        }

        jPanel.add(jButton);
        jPanel.add(jTextField);
        jPanel.add(jLabel);
        drinkPanel.add(jPanel);
    }

    @Override
    public void setSelectedDrink(Drink drink) {
        selectedDrinkLabel.setText(drink.getName() + " --- " + CurrencyHelper.toCoins(drink.getValue()));
        coinCollectTf.setText(NO_COINS_TEXT);
        enableCoinButtons();
    }

    @Override
    public void disableTransactions() {
        Component[] comp = coinPanel.getComponents();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i] instanceof JButton) {
                ((JButton) comp[i]).setEnabled(false);
            }
        }
        comp = drinkPanel.getComponents();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i] instanceof JPanel) {
                Component[] drinkComp = ((JPanel) comp[i]).getComponents();
                for (int j = 0; j < drinkComp.length; j++) {
                    if (drinkComp[j] instanceof JButton) {
                        JButton jButton = ((JButton) drinkComp[j]);
                        jButton.setEnabled(false);
                    }
                }
            }
        }
    }

    @Override
    public void enableTransactions() {
        disableCoinButtons();
    }

    @Override
    public void refreshDrinkPanel(List<Drink> drinkList) {
        drinkPanel.removeAll();
        Iterator<Drink> iterator = drinkList.iterator();
        while (iterator.hasNext()) {
            Drink drink = iterator.next();

            JPanel jPanel = new JPanel();
            jPanel.setLayout(new GridLayout());

            JButton jButton = new JButton();
            jButton.setFont(new java.awt.Font("Tahoma", 0, 14));
            jButton.setText(drink.getName());
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    //CustomerControllerImpl.get().startTransaction(drink);
                }
            });

            JTextField jTextField = new JTextField();
            jTextField.setText(CurrencyHelper.toCoins(drink.getValue()));
            jTextField.setEditable(false);
            jTextField.setBackground(new java.awt.Color(0, 0, 0));
            jTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jTextField.setForeground(new java.awt.Color(255, 255, 255));
            jTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

            JLabel jLabel = new JLabel();
            jLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel.setForeground(java.awt.Color.red);
            jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            if (drink.getQuantity() == 0) {
                jLabel.setText(NOT_IN_STOCK_TEXT);
                jLabel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.red));
                jButton.setEnabled(false);
            }

            jPanel.add(jButton);
            jPanel.add(jTextField);
            jPanel.add(jLabel);
            drinkPanel.add(jPanel);
        }
        jFrame.revalidate();
        jFrame.repaint();
    }

    @Override
    public String getInsertedAmount() {
        return insertedAmountTf.getText();
    }

    @Override
    public void dispenseDrink(Drink drink) {
        selectedDrinkLabel.setText(null);
        collectCanTf.setText(drink.getName());
        terminateButton.setEnabled(false);
    }

    @Override
    public void displayChange(double originalChange, double changeAvailable) {
        if (changeAvailable < originalChange) {
            noChangeLabel.setText(INSUFFICIENT_CHANGE_TEXT);
        }
        coinCollectTf.setText(CurrencyHelper.toCoins(changeAvailable));
    }

    @Override
    public void resetState() {
        terminateButton.setEnabled(false);
        noChangeLabel.setText(null);
        insertedAmountTf.setText(NO_COINS_TEXT);
        coinCollectTf.setText(NO_COINS_TEXT);
        collectCanTf.setText(NO_CAN_TEXT);
        disableCoinButtons();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        invalidCoinLabel = new javax.swing.JLabel();
        coinPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        insertedAmountTf = new javax.swing.JTextField();
        drinkPanel = new javax.swing.JPanel();
        noChangeLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        terminateButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        coinCollectTf = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        collectCanTf = new javax.swing.JTextField();
        selectedDrinkLabel = new javax.swing.JLabel();

        jFrame.setTitle("VMCS - Customer Panel");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Soft Drink Dispenser");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Enter Coins Here");

        invalidCoinLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        invalidCoinLabel.setForeground(java.awt.Color.red);
        invalidCoinLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        invalidCoinLabel.setPreferredSize(new java.awt.Dimension(0, 20));

        coinPanel.setMinimumSize(new java.awt.Dimension(50, 50));
        coinPanel.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Total Amount of Money Inserted: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel4, gridBagConstraints);

        insertedAmountTf.setEditable(false);
        insertedAmountTf.setBackground(new java.awt.Color(0, 0, 0));
        insertedAmountTf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        insertedAmountTf.setForeground(new java.awt.Color(255, 255, 255));
        insertedAmountTf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        insertedAmountTf.setText("0 c");
        insertedAmountTf.setPreferredSize(new java.awt.Dimension(100, 27));
        jPanel2.add(insertedAmountTf, new java.awt.GridBagConstraints());

        drinkPanel.setPreferredSize(new java.awt.Dimension(100, 100));
        drinkPanel.setLayout(new java.awt.GridLayout(0, 1));

        noChangeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        noChangeLabel.setForeground(java.awt.Color.red);
        noChangeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noChangeLabel.setPreferredSize(new java.awt.Dimension(0, 20));

        terminateButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        terminateButton.setText("Terminate and Return Cash");
        terminateButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        terminateButton.setPreferredSize(new java.awt.Dimension(201, 30));
        terminateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminateButtonActionPerformed(evt);
            }
        });
        jPanel4.add(terminateButton);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Collect Coins:      ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(jLabel6, gridBagConstraints);

        coinCollectTf.setEditable(false);
        coinCollectTf.setBackground(new java.awt.Color(0, 0, 0));
        coinCollectTf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        coinCollectTf.setForeground(new java.awt.Color(255, 255, 255));
        coinCollectTf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        coinCollectTf.setText("0 c");
        coinCollectTf.setPreferredSize(new java.awt.Dimension(100, 27));
        jPanel5.add(coinCollectTf, new java.awt.GridBagConstraints());

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Collect Can Here: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel6.add(jLabel7, gridBagConstraints);

        collectCanTf.setEditable(false);
        collectCanTf.setBackground(new java.awt.Color(0, 0, 0));
        collectCanTf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        collectCanTf.setForeground(new java.awt.Color(255, 255, 255));
        collectCanTf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        collectCanTf.setText("NO CAN");
        collectCanTf.setPreferredSize(new java.awt.Dimension(100, 27));
        jPanel6.add(collectCanTf, new java.awt.GridBagConstraints());

        selectedDrinkLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectedDrinkLabel.setForeground(java.awt.Color.red);
        selectedDrinkLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectedDrinkLabel.setPreferredSize(new java.awt.Dimension(0, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jFrame.getContentPane());
        jFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(invalidCoinLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(coinPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(drinkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noChangeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectedDrinkLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(coinPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(invalidCoinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(selectedDrinkLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(drinkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(noChangeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void enableTerminateButton() {
        terminateButton.setEnabled(true);
    }

    @Override
    public void disableTerminateButton() {
        terminateButton.setEnabled(false);
    }

    @Override
    public void terminateTransaction() {
        disableCoinButtons();
        selectedDrinkLabel.setText(null);
        coinCollectTf.setText(insertedAmountTf.getText());
        insertedAmountTf.setText(NO_COINS_TEXT);
    }

    @Override
    public void refresh() {
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
    }

    @Override
    public void show() {
        jFrame.setVisible(true);
    }

    @Override
    public void hide() {
        jFrame.setVisible(false);
    }

    private void terminateButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_terminateButtonActionPerformed
        // TODO add your handling code here:
        //CustomerControllerImpl.get().terminateTransaction();
    }// GEN-LAST:event_terminateButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField coinCollectTf;
    private javax.swing.JPanel coinPanel;
    private javax.swing.JTextField collectCanTf;
    private javax.swing.JPanel drinkPanel;
    private javax.swing.JTextField insertedAmountTf;
    private javax.swing.JLabel invalidCoinLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel noChangeLabel;
    private javax.swing.JLabel selectedDrinkLabel;
    private javax.swing.JButton terminateButton;
    private javax.swing.JFrame jFrame;
    // End of variables declaration//GEN-END:variables

}
