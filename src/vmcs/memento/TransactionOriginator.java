package vmcs.memento;

import vmcs.model.Stock;

public class TransactionOriginator {

    private Stock stock;

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Stock getStock() {
        return stock;
    }

    public TransactionMemento saveStateToMemento() {
        return new TransactionMemento(stock);
    }

    public void getStateFromMemento(TransactionMemento memento) {
        stock = memento.getStock();
    }

}
