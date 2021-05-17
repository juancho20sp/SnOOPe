package domain.edibles;

import domain.players.SuperPlayer;

import java.awt.*;
import java.io.Serializable;

public class Brick extends PowerUp implements Serializable {
    /**
     * Constructor of the Bad Arrow class
     * @param x The x position of the power up
     * @param y The y position of the power up
     * @param color The background color of the power up
     * @param points The points given by catching the power up
     */
    public Brick(int x, int y, Color color, int points) {
        super(x, y, color, points);

        super.setImage("./images/brick.png");
    }

    /**
     * Method for using a PowerUp
     */
    @Override
    public void usePowerUp(SuperPlayer player){
        int oldFrequency = player.getSnake().getFrequency();

        player.getSnake().setFrequency(40);


        System.out.println("power up used");
    }
}
