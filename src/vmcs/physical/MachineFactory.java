package vmcs.physical;

public class MachineFactory {

    private static volatile Machine sSoleInstance;

    public static Machine getMachine() {
        if (sSoleInstance == null) {
            synchronized (Machine.class) {
                if (sSoleInstance == null) {
                    sSoleInstance = new MachineImpl();
                }
            }
        }
        return sSoleInstance;
    }
}
