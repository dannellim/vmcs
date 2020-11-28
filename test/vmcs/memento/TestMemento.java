package vmcs.memento;

import static junit.framework.TestCase.*;
import org.junit.Test;
import vmcs.model.Coin;

public class TestMemento {

    @Test
    public void test() {

        TransactionOriginator originator = new TransactionOriginator();
        TransactionCaretaker careTaker = new TransactionCaretaker();

        assertNotNull(originator);
        assertNotNull(careTaker);

        for (int i = 0; i < 100; i++) {
            Coin coin = new Coin();
            coin.setName(String.valueOf(i));
            coin.setQuantity(i);
            coin.setValue(i);
            originator.setStock(coin);
            careTaker.addMemento(originator.saveStateToMemento());
        }

        for (int i = 100; i < 200; i++) {
            Coin coin = new Coin();
            coin.setName(String.valueOf(i));
            coin.setQuantity(i);
            coin.setValue(i);
            originator.setStock(coin);
            careTaker.addMemento(originator.saveStateToMemento());
        }

        for (int i = 0; i < 100; i++) {
            Coin coin = new Coin();
            coin.setName(String.valueOf(i));
            coin.setQuantity(i);
            coin.setValue(i);
            originator.getStateFromMemento(careTaker.get(i));
            Coin coin1 = (Coin) originator.getStock();
            assertEquals(coin, coin1);
        }

        for (int i = 100; i < 200; i++) {
            Coin coin = new Coin();
            coin.setName(String.valueOf(i));
            coin.setQuantity(i);
            coin.setValue(i);
            originator.getStateFromMemento(careTaker.get(i));
            Coin coin1 = (Coin) originator.getStock();
            assertEquals(coin, coin1);
        }

    }

}
