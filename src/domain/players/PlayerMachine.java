package domain.players;

public class PlayerMachine extends Player{
    public PlayerMachine(){
        super();

        // Name
        this.setName();
    }

    /**
     * Method for setting up the name of the machine
     */
    private void setName(){
        super.setName("La máquina");
    }
}
