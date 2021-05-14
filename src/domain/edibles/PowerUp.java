package domain.edibles;

import domain.Game;
import domain.players.SuperPlayer;

import java.awt.*;
import java.io.Serializable;

public class PowerUp extends Edible implements Serializable {

    public PowerUp(int x, int y, Color color, int points) {
        super(x, y, color, points);
    }

    /**
     * Method for eating a power up
     */
    public void eatPowerUp(PowerUp edible, SuperPlayer player, Game game){
        // Add points
        int newPoints = player.getPoints() + edible.getPoints();
        player.setPoints(newPoints);

        // Delete the coordinate
        game.updateCoordinates(edible.getX(), edible.getY(), 0);

        // Add to the stack
        player.setPowerUp(edible);

        System.out.println("JUGADOR 1 + FLECHAS DE VELOCIDAD");
    }

    /**
     * Method for using a PowerUp
     */
    public void usePowerUp(SuperPlayer player){ }
}
