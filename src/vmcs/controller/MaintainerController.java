package vmcs.controller;

import vmcs.model.Drink;
import vmcs.model.Stock;

import java.util.List;

public interface MaintainerController {
    void setSelectedDrink(Drink drink);

    void lock();

    void unlock();

    void showPanel();

    void closePanel();

    void logIn();

    void unLogIn();

    void checkPassword(String password);

    boolean validatePassword(String password);

    void setDrinkStocks(List<Stock> drinkList);

    void setCoinStocks(List<Stock> coinList);

    void collectAllCash();

    void showTotalCashHeld();

    double getTotalCash(double total);

    void changePrice(String newValue);
}
