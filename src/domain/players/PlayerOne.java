package domain.players;

public class PlayerOne extends Player{
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
