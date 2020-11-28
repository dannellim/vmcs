package vmcs.memento;

import vmcs.model.Stock;

public class TransactionMemento {

    private Stock stock;

    public TransactionMemento(Stock stock) {
        this.stock = stock;
    }

    public Stock getStock() {
        return stock;
    }

}
