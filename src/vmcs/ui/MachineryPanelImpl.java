/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.ui;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import vmcs.controller.MachineryController;
import vmcs.controller.MachineryControllerImpl;
import vmcs.model.Coin;
import vmcs.model.Drink;

/**
 *
 * @author Dannel
 */
public class MachineryPanelImpl implements MachineryPanel {

    private final HashMap<Integer, JTextField> drinkQtyTextField;
    private final HashMap<String, JTextField> coinQtyTextField;
    private MachineryController machineryController;

    /**
     * Creates new form MachineryPanel
     */
    
    private static volatile MachineryPanel sSoleInstance;

    public static MachineryPanel getInstance() {
        if (sSoleInstance == null) {
            synchronized (MachineryPanel.class) {
                if (sSoleInstance == null) {
                    sSoleInstance = new MachineryPanelImpl();
                }
            }
        }
        return sSoleInstance;
    }

    private MachineryPanelImpl() {
        initComponents();
        drinkQtyTextField = new HashMap<>();
        coinQtyTextField = new HashMap<>();
        machineryController = new MachineryControllerImpl(this);
        machineryController.init();
    }

    @Override
    public void addCoinToUI(Coin coin) {
        if (!coin.getName().equalsIgnoreCase("Invalid")) {
            JLabel coinLabel = new JLabel();
            coinLabel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            coinLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
            coinLabel.setText(coin.getName());

            JTextField coinQty = quantityUI(coin.getQuantity());

            coinPanel.add(coinLabel);
            coinPanel.add(coinQty);
            coinQty.addActionListener(actionEvent -> {

                int qty = Integer.valueOf(coinQty.getText());
                if (qty >= 0 && qty <= 40) {
                    machineryController.changeCoinStock(coin,qty);

                } else {
                    JOptionPane.showMessageDialog(jFrame,
                            "Quantity must be between 0 and 40");

                }
            });
            coinQtyTextField.put(coin.getName(), coinQty);

        }
    }

    @Override
    public void addDrinkToUI(final Drink drink) {
        JLabel drinkLabel = new JLabel();
        drinkLabel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        drinkLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
        drinkLabel.setText(drink.getName().replace("_", " ").toUpperCase());

        JTextField drinkQty = quantityUI(drink.getQuantity());

        drinkQty.addActionListener(actionEvent -> {
            int qty = Integer.valueOf(drinkQty.getText());
            if (qty >= 0 && qty <= 20) {
                machineryController.changeDrinkStock(drink,qty);

            } else {
                JOptionPane.showMessageDialog(jFrame,
                        "Quantity must be between 0 and 20");
            }
        });

        drinkPanel.add(drinkLabel);
        drinkPanel.add(drinkQty);
        drinkQtyTextField.put(drink.hashCode(), drinkQty);

    }

    private JFormattedTextField quantityUI(int quantity) {
        NumberFormat longFormat = NumberFormat.getIntegerInstance();

        NumberFormatter numberFormatter = new NumberFormatter(longFormat);
        numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
        numberFormatter.setAllowsInvalid(false); //this is the key!!
        numberFormatter.setMinimum(0l); //Optional

        JFormattedTextField quantityTextField = new JFormattedTextField(numberFormatter);
        quantityTextField.setEnabled(false);

        quantityTextField.setBackground(new java.awt.Color(0, 0, 0));
        quantityTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quantityTextField.setForeground(java.awt.Color.YELLOW);
        quantityTextField.setHorizontalAlignment(JTextField.CENTER);
        quantityTextField.setText(String.valueOf(quantity));
        quantityTextField.setDisabledTextColor(java.awt.Color.YELLOW);

        return quantityTextField;
    }

    @Override
    public void updateDrinkUI(Drink drink) {
        drinkQtyTextField.get(drink.hashCode()).setText(String.valueOf(drink.getQuantity()));
    }

    @Override
    public void updateCoinUI(Coin coin) {
        if (coinQtyTextField.get(coin.getName()) != null) {
            coinQtyTextField.get(coin.getName()).setText(String.valueOf(coin.getQuantity()));
        }

    }

    @Override
    public void changeTextFieldState(boolean state) {
        coinQtyTextField.values().forEach(v -> {
            System.out.println("Coin ui update");
            changeTextFieldState(state, v);

        });
        drinkQtyTextField.values().forEach(textField -> {
            System.out.println("Drink ui update");

            changeTextFieldState(state, textField);
        });
    }

    private void changeTextFieldState(boolean state, JTextField v) {
        if (state) {
            v.setEnabled(false);

            v.setBackground(new Color(0, 0, 0));
            v.setFont(new Font("Tahoma", 0, 14)); // NOI18N
            v.setForeground(Color.YELLOW);
        } else {
            v.setEnabled(true);

            v.setBackground(new Color(255, 255, 255));
            v.setFont(new Font("Tahoma", 0, 14)); // NOI18N
            v.setForeground(Color.BLACK);
        }
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

	jFrame = new javax.swing.JFrame ();
        header = new javax.swing.JLabel();
        coinQtyLabel = new javax.swing.JLabel();
        coinPanel = new javax.swing.JPanel();
        drinkQtyLabel = new javax.swing.JLabel();
        drinkPanel = new javax.swing.JPanel();
        doorStateCheckBox = new javax.swing.JCheckBox();

        jFrame.setTitle("VMCS - Machinery Panel");

        header.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("Machinery Panel");

        coinQtyLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        coinQtyLabel.setText("Quantity of Coins Available:");

        coinPanel.setLayout(new java.awt.GridLayout(0, 2));

        drinkQtyLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        drinkQtyLabel.setText("Quantity of Drinks Available:");

        drinkPanel.setLayout(new java.awt.GridLayout(0, 2));

        doorStateCheckBox.setText("Door Locked");
        doorStateCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        doorStateCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doorStateCheckBoxItemStateChanged();

            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jFrame.getContentPane());
        jFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(coinPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(drinkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(coinQtyLabel)
                            .addComponent(drinkQtyLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(doorStateCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header)
                .addGap(18, 18, 18)
                .addComponent(coinQtyLabel)
                .addGap(18, 18, 18)
                .addComponent(coinPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(drinkQtyLabel)
                .addGap(18, 18, 18)
                .addComponent(drinkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(doorStateCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jFrame.pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doorStateCheckBoxItemStateChanged() {// GEN-FIRST:event_doorStateCheckBoxItemStateChanged
        // TODO add your handling code here:
        if (doorStateCheckBox.isSelected()) {
            machineryController.lockDoor();
        } else {
            machineryController.unLockDoor();
        }

    }// GEN-LAST:event_doorStateCheckBoxItemStateChanged

    @Override
    public void updateDoorLockState(boolean isLocked) {
        doorStateCheckBox.setSelected(isLocked);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox doorStateCheckBox;
    private javax.swing.JLabel header;
    private javax.swing.JLabel coinQtyLabel;
    private javax.swing.JLabel drinkQtyLabel;
    private javax.swing.JPanel coinPanel;
    private javax.swing.JPanel drinkPanel;
    private javax.swing.JFrame jFrame;
    // End of variables declaration//GEN-END:variables

    @Override
    public void show() {
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    @Override
    public void hide() {
        jFrame.setVisible(false);
    }

    @Override
    public void refresh() {
        jFrame.pack();
        //jFrame.setLocationRelativeTo(null);
    }

    @Override
    public MachineryController getController() {
        return this.machineryController;
    }
}
