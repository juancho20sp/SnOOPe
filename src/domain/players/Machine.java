package domain.players;


import domain.Game;
import domain.snakes.SnakeMachine;

import java.io.Serializable;

public class Machine extends SuperPlayer implements Serializable {
    // Machine types
    public static final String DISTRACTED = "distracted";
    public static final String CAUTIOUS = "cautious";
    public static final String GLUTTON = "glutton";

    // Machine type
    private String machineType;

    /**
     * Method for creating a new snake
     */
    public void createSnake(int[] headPos, int[] tailPos, Game game){
        this.setSnake(new SnakeMachine(3, headPos, tailPos, super.getHeadColor(), super.getBodyColor(), game));
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        System.out.println("new machine type: " + machineType);
        this.machineType = machineType;
    }
}
