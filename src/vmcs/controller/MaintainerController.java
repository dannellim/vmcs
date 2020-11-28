package vmcs.controller;

import vmcs.model.Drink;
import vmcs.model.Stock;

import java.util.List;

public interface MaintainerController {
    void setSelectedDrink(Drink drink);

    void lock();

    void unlock();

    void logIn();

    void unLogIn();

    void checkPassword(String password);

    boolean validatePassword(String password);

    void collectAllCash();

    void showTotalCashHeld();

    void changePrice(String newValue);
    
    boolean isLock();
}
