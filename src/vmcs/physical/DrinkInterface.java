package vmcs.physical;


import vmcs.model.Drink;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class DrinkInterface implements Observer {
    DrinkInterfaceListener drinkInterfaceListener;
    List<Drink> drinks;

    public DrinkInterface(DrinkInterfaceListener drinkInterfaceListener, List<Drink> drinkList) {
        this.drinkInterfaceListener = drinkInterfaceListener;
        this.drinks=drinkList;
        drinks.forEach(coin -> {
            coin.addObserver(this);
        });
    }

    void dispenseDrink(Drink drink){
        drinkInterfaceListener.onDrinkDispensed(drink);
        Drink drinkInStorage=
                drinks.get(drinks.indexOf(drink));
        drinkInStorage.decreaseCoin();

    }

    public void updateDrinkStock(Drink drink,int qty){
        Drink drinkInStorage=
                drinks.get(drinks.indexOf(drink));
        drinkInStorage.setQuantity(qty);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof Drink){;
            drinkInterfaceListener.onDrinkStockChanged((Drink) o);
        }
    }

    public List<Drink> getDrinkStock(){
        return  drinks;
    }

    public interface DrinkInterfaceListener {
        void onDrinkDispensed(Drink drink);

        void onDrinkStockChanged(Drink drink);

    }
}
