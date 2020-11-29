package vmcs.model;

public class DoorState {

    private boolean isLocked;
    private DoorStateChangeListener doorStateChangeListener;

    private static DoorState doorState;

    private DoorState(DoorStateChangeListener doorStateChangeListener) {
        this.doorStateChangeListener = doorStateChangeListener;
        isLocked = true;
    }

    public static DoorState getInstance(DoorStateChangeListener doorStateChangeListener) {
        if (doorState == null) {
            synchronized (DoorState.class) {
                if (doorState == null) {
                    doorState = new DoorState(doorStateChangeListener);
                }
            }
        }
        return doorState;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
        doorStateChangeListener.onDoorStateChange(isLocked);
    }

    public interface DoorStateChangeListener {

        void onDoorStateChange(boolean isLock);
    }
}
