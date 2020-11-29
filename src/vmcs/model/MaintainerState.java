package vmcs.model;

import java.util.Observable;

public class MaintainerState extends Observable {

    private boolean isLogIn;

    private static MaintainerState maintainerState;

    private MaintainerState() {
        isLogIn = false;
    }

    public static MaintainerState getInstance() {
        if (maintainerState == null) {
            synchronized (MaintainerState.class) {
                if (maintainerState == null) {
                    maintainerState = new MaintainerState();
                }
            }
        }
        return maintainerState;
    }

    public boolean isLogIn() {
        return isLogIn;
    }

    public void setLogIn(boolean isLogIn) {
        this.isLogIn = isLogIn;
        setChanged();
        notifyObservers();
    }

}
