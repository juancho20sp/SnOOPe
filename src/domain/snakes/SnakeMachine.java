package domain.snakes;

import domain.Game;
import domain.directions;

import java.awt.*;
import java.util.Random;

import static domain.players.Machine.*;

public class SnakeMachine extends SuperSnake{
    private Random random = new Random();

    /**
     * Constructor for the SnakeP1 class
     * @param size The size of the snake
     * @param headPosition The position of the head of the snake
     * @param headColor The head color of the snake
     * @param bodyColor The body color of the snake
     */
    public SnakeMachine(int size, int[] headPosition, int[] tailPosition, Color headColor, Color bodyColor, Game game) {
        super(size, headPosition, headColor, bodyColor, game);

        // Snake positions
        super.setHeadPosition(headPosition);
        // Está mirando a la derecha
        if (headPosition[0] > tailPosition[0]){
            for (int i = 1; i <= size - 1; i++) {
                super.addPosition(new int[]{headPosition[0] - i, headPosition[1]});
            }
        } else {
            for (int i = 1; i <= size - 1; i++) {
                super.addPosition(new int[]{headPosition[0] + i, headPosition[1]});
            }
        }


        // Snake direction
        super.setDirection(directions.LEFT);
    }

    /**
     * Method for moving the snake
     */
    @Override
    public void move(){
        switch (game.getGameData().getPlayerMachine().getMachineType()){
            case DISTRACTED:
                moveAsDistracted();
                break;
            case CAUTIOUS:
                break;
            case GLUTTON:
                break;
        }
    }

    /**
     * Method for moving as distracted
     */
    private void moveAsDistracted(){
        int num = random.nextInt(4);

        switch (num){
            case 0:
                if (getDirection() != directions.DOWN){
                    setDirection(directions.UP);
                }
                break;
            case 1:
                if (getDirection() != directions.UP){
                    setDirection(directions.DOWN);
                }
                break;
            case 2:
                if (getDirection() != directions.RIGHT){
                    setDirection(directions.LEFT);
                }
                break;
            case 3:
                if (getDirection() != directions.LEFT){
                    setDirection(directions.RIGHT);
                }
                break;
        }
    }
}