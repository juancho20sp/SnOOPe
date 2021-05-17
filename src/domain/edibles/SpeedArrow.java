package domain.edibles;

import domain.Game;
import domain.players.SuperPlayer;

import java.awt.*;
import java.beans.Transient;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class SpeedArrow extends PowerUp implements Serializable {
    transient Timer timer;

    /**
     * Constructor of the Arrow class
     * @param x The x position of the power up
     * @param y The y position of the power up
     * @param color The background color of the power up
     * @param points The points given by catching the power up
     */
    public SpeedArrow(int x, int y, Color color, int points) {
        super(x, y, color, points);

        super.setImage("./images/arrow.png");

    }

    /**
     * Method for using a PowerUp
     */
    @Override
    public void usePowerUp(SuperPlayer player){
        int oldFrequency = player.getSnake().getFrequency();

        player.getSnake().setFrequency(40);


        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                player.getSnake().setFrequency(oldFrequency);
            }
        };

        timer.schedule(timerTask, 5000);







        System.out.println("power up used");
    }





}
