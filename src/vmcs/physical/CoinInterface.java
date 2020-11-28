package vmcs.physical;

import vmcs.model.Coin;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class CoinInterface implements Observer {

    CoinInterfaceListener coinInterfaceListener;
    List<Coin> coins;

    public CoinInterface(CoinInterfaceListener coinInterfaceListener, List<Coin> coins) {
        this.coinInterfaceListener = coinInterfaceListener;
        this.coins = coins;
        coins.forEach(coin -> {
            coin.addObserver(this);
        });
    }

    public List<Coin> getCoinStock() {
        return coins;
    }

    public void updateCoinStock(Coin coin, int qty) {
        Coin coinInStorage
                = coins.get(coins.indexOf(coin));
        coinInStorage.setQuantity(qty);
    }

    public void insertCoin(Coin coin) {
        if (coin.getValue() != 0) {
            Coin coinInStorage
                    = coins.get(coins.indexOf(coin));
            coinInStorage.increaseStock();
            coinInterfaceListener.onCoinAccepted(coin);
        } else {
            //dispense(coin);
            coinInterfaceListener.onCoinRejected(coin);
        }
    }

    public void dispense(Coin coin) {
        //if hardware ,we tell hardware to dispense coin
        Coin coinInStorage
                = coins.get(coins.indexOf(coin));
        coinInStorage.decreaseStock();
        coinInterfaceListener.onCoinDispensed(coin);

    }

    public void dispense(List<Coin> coins) {
        //if hardware ,we tell hardware to dispense coin
        for (Coin coin : coins) {
            Coin coinInStorage
                    = coins.get(coins.indexOf(coin));
            coinInStorage.decreaseStock();
        }
        coinInterfaceListener.onCoinDispensed(coins);

    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof Coin) {
            coinInterfaceListener.onCoinStockChanged((Coin) observable);

        }
    }

    public interface CoinInterfaceListener {

        void onCoinAccepted(Coin coin);

        void onCoinRejected(Coin coin);

        void onCoinDispensed(Coin coin);

        void onCoinDispensed(List<Coin> coin);

        void onCoinStockChanged(Coin coin);

    }

}
