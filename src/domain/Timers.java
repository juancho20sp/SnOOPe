package domain;

import java.util.Timer;
import java.util.TimerTask;

public class Timers {
    transient Timer timer;

    // Game
    private Game game;

    /**
     * Constructor of the 'Timers' class
     */
    public Timers(Game game){
        this.game = game;
    }

    /**
     * Method for setting the timer of the fruits
     */
    public void fruitTimer(){
        timer = new Timer();
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run(){
                game.addFruit1();
            }
        };

        timer.schedule(timerTask, game.getGameData().getFruitsTimer());
    }
}
