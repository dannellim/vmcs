package vmcs.controller;

import vmcs.model.Drink;

public interface MaintainerController {
    void setSelectedDrink(Drink drink);
    
    Drink getSelectedDrink();

    void lock();

    void unlock();

    void logIn();

    void unLogIn();

    void checkPassword(String password);

    boolean validatePassword(String password);

    void collectAllCash();

    int showTotalCashHeld();

    void changePrice(String newValue);
    
    boolean isLock();
}
