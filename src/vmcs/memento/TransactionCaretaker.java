package vmcs.memento;

import java.util.ArrayList;
import java.util.List;

public class TransactionCaretaker {

    private List<TransactionMemento> mementoList = new ArrayList<>();

    public void addMemento(TransactionMemento tm) {
        mementoList.add(tm);
    }

    public TransactionMemento get(int index) {
        return mementoList.get(index);
    }

    public int size() {
        return mementoList.size();
    }

}
