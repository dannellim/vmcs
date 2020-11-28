package vmcs.physical;

public class MachineFactory {

    public static Machine machine=null;
    public  static Machine getMachine(){
        if(machine==null){
            machine=new MachineImpl();
        }
        return machine;
    }
}
