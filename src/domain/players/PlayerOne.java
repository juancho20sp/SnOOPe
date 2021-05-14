package domain.players;

import java.io.Serializable;

public class PlayerOne extends Player implements Serializable {
    public PlayerOne() {
        super();

        // Number
        this.setupNumber();
    }

    /**
     * Method for setting up the number of the player
     */
    private void setupNumber(){
        super.setNumber(1);
    }
}
