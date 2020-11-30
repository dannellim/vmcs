package vmcs.model;

public class FaultState {

    private boolean isFault;
    private FaultStateChangeListener faultStateChangeListener;

    private static FaultState doorState;

    private FaultState(FaultStateChangeListener faultStateChangeListener) {
        this.faultStateChangeListener = faultStateChangeListener;
        isFault = false;
    }

    public static FaultState getInstance(FaultStateChangeListener faultStateChangeListener) {
        if (doorState == null) {
            synchronized (FaultState.class) {
                if (doorState == null) {
                    doorState = new FaultState(faultStateChangeListener);
                }
            }
        }
        return doorState;
    }

    public boolean isFault() {
        return isFault;
    }

    public void setFault(boolean isFault) {
        this.isFault = isFault;
        faultStateChangeListener.onFaultStateChange(isFault);
    }

    public interface FaultStateChangeListener {

        void onFaultStateChange(boolean isFault);
    }
}
