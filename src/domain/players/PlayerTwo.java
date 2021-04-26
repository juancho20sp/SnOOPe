package domain.players;

public class PlayerTwo extends Player{
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
