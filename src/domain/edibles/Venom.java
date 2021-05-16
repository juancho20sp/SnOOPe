package domain.edibles;

import domain.Game;
import domain.players.SuperPlayer;

import java.awt.*;
import java.io.Serializable;

public class Venom extends Edible implements Serializable {
    /**
     * Constructor of the Venom class
     * @param x The x position of the apple
     * @param y The y position of the apple
     * @param color The color of the apple
     */
    public Venom(int x, int y, Color color) {
        super(x, y, color, 0);

        super.setImage("./images/skull.png");
    }

    /**
     * Method that overrides the 'eatEdible' method
     */
    @Override
    public void eatEdible(Edible edible, SuperPlayer player, Game game){
        game.getGameData().setGameRunning(false);
    }
}
