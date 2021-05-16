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
