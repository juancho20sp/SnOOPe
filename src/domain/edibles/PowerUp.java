package domain.edibles;

import domain.Game;
import domain.players.SuperPlayer;

import java.awt.*;
import java.io.Serializable;

public class PowerUp extends Edible implements Serializable {
    // Game
    Game game;

    public PowerUp(int x, int y, Color color, int points) {
        super(x, y, color, points);
    }

    /**
     * Method for eating a power up
     */
    public void eatPowerUp(PowerUp edible, SuperPlayer player, Game game){
        this.game = game;

        System.out.println("POWER UP EATEN");

        // Add points
        int newPoints = player.getPoints() + edible.getPoints();
        player.setPoints(newPoints);

        // Add to the stack
        player.setPowerUp(edible);

        // Clear the position
        game.getBoard().cleanPosition(edible.getX(), edible.getY());
    }

    /**
     * Method for using a PowerUp
     */
    public void usePowerUp(SuperPlayer player, SuperPlayer player2){ }
}
