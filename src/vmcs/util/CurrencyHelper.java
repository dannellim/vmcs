/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmcs.util;

/**
 *
 * @author Dannel
 */
public class CurrencyHelper {

    public static String toCoins(double amount) {
        return (int) (amount * 100.0) + " c";
    }

    public static double coinsToAmount(String coins) {
        double results = 0;
        if (coins.contains("c")) {
            coins = coins.replace("c", "").trim();
            results = Double.parseDouble(String.format("%.2f", Double.parseDouble(coins) / 100.0));
        } else if (coins.contains("$")) {
            coins = coins.replace("$", "");
            results = Double.parseDouble(String.format("%.2f", Double.parseDouble(coins)));
        }
        return results;
    }

    public static double subtract(double a, double b) {
        return Double.parseDouble(String.format("%.2f", (a - b)));
    }

    public static double add(double a, double b) {
        return Double.parseDouble(String.format("%.2f", (a + b)));
    }
}
