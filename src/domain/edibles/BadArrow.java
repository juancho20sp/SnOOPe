package domain.edibles;

import domain.players.SuperPlayer;

import java.awt.*;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class BadArrow extends PowerUp implements Serializable {
    transient Timer timer;

    /**
     * Constructor of the Bad Arrow class
     * @param x The x position of the power up
     * @param y The y position of the power up
     * @param color The background color of the power up
     * @param points The points given by catching the power up
     */
    public BadArrow(int x, int y, Color color, int points) {
        super(x, y, color, points);

        super.setImage("./images/badArrow.png");
    }

    /**
     * Method for using a PowerUp
     */
    public void usePowerUp(SuperPlayer player, SuperPlayer player2){
        if (super.game.isSinglePlayer()){
            int oldFrequency = player.getSnake().getFrequency();
            player.getSnake().setFrequency(150);

            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    player.getSnake().setFrequency(oldFrequency);
                }
            };

            timer.schedule(timerTask, 5000);
        } else {
            int oldFrequency = player2.getSnake().getFrequency();
            player2.getSnake().setFrequency(150);

            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    player2.getSnake().setFrequency(oldFrequency);
                }
            };

            timer.schedule(timerTask, 5000);
        }

        player.setPowerUp(null);
    }
}
