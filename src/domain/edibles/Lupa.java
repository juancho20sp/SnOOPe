package domain.edibles;

import domain.players.SuperPlayer;

import java.awt.*;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Lupa extends PowerUp implements Serializable {
    /**
     * Constructor of the Bad Arrow class
     * @param x The x position of the power up
     * @param y The y position of the power up
     * @param color The background color of the power up
     * @param points The points given by catching the power up
     */
    public Lupa(int x, int y, Color color, int points) {
        super(x, y, color, points);

        super.setImage("./images/lupa.png");
    }

    /**
     * Method for using a PowerUp
     */
    public void usePowerUp(SuperPlayer player, SuperPlayer player2){
        int oldFrequency = player.getSnake().getFrequency();

        player.getSnake().setFrequency(40);

        System.out.println("power up used");
    }
}
