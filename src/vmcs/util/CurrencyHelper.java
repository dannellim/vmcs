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

    public static String toCoins(int amount) {
        return amount + " c";
    }

    public static int coinsToAmount(String coins) {
        int results = 0;
        if (coins.contains("c")) {
            coins = coins.replace("c", "").trim();
            results = Integer.parseInt(coins);
        } else if (coins.contains("$")) {
            coins = coins.replace("$", "");
            results = Integer.parseInt(coins) * 100;
        }
        return results;
    }
}
