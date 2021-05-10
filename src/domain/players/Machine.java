package domain.players;


public class Machine extends SuperPlayer{
    // Machine types
    public static final String DISTRACTED = "distracted";
    public static final String CAUTIOUS = "cautious";
    public static final String GLUTTON = "glutton";

    // Machine type
    private String machineType;


    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        System.out.println("new machine type: " + machineType);
        this.machineType = machineType;
    }
}
