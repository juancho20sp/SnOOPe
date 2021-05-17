package domain.edibles;

import domain.players.SuperPlayer;

import java.awt.*;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Division extends PowerUp implements Serializable {
    /**
     * Constructor of the Bad Arrow class
     * @param x The x position of the power up
     * @param y The y position of the power up
     * @param color The background color of the power up
     * @param points The points given by catching the power up
     */
    public Division(int x, int y, Color color, int points) {
        super(x, y, color, points);

        super.setImage("./images/division.png");
    }

    /**
     * Method for using a PowerUp
     */
    @Override
    public void usePowerUp(SuperPlayer player, SuperPlayer player2){
        if (super.game.isSinglePlayer()){
            int size = player.getSnake().getPositions().size();
            double mid = (float)size / 2;
            int half = (int)Math.ceil(mid);

            player.getSnake().decreaseSize(half);

        } else {
            int size = player2.getSnake().getPositions().size();
            double mid = (float)size / 2;
            int half = (int)Math.ceil(mid);

            player2.getSnake().decreaseSize(half);
        }

        player.setPowerUp(null);
    }
}
