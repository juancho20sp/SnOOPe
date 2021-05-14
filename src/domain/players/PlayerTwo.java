package domain.players;

import java.io.Serializable;

public class PlayerTwo extends Player implements Serializable {
    public PlayerTwo() {
        super();
        // Number
        this.setupNumber();
    }

    /**
     * Method for setting up the number of the player
     */
    private void setupNumber(){
        super.setNumber(2);
    }
}
