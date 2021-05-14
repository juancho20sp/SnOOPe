package domain.snakes;

import domain.Game;
import domain.directions;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

import static domain.players.Machine.*;

public class SnakeMachine extends SuperSnake implements Serializable {
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
                moveAsGlutton();
                break;
        }
    }

    /**
     * Method for changing positions
     * @param cols The number of cols of the board
     * @param rows The number of rows of the board
     * @return
     */
    @Override
    public void updatePositions(int rows, int cols){
        int x = this.getHeadPosition()[0];
        int y = this.getHeadPosition()[1];


        switch (this.getDirection()){
            case UP:
                y = y-1;
                this.setHeadPosition(new int[]{x, y});

                if (y >= rows - 1){
                    this.setHeadPosition(new int[]{x, 0});
                    //game.getGameData().setGameRunning(false);
                }

                if (y <= 0){
                    this.setHeadPosition(new int[]{x, rows - 1});
                    //game.getGameData().setGameRunning(false);
                }
                break;

            case DOWN:
                y = y + 1;
                this.setHeadPosition(new int[]{x, y});

                if (y >= rows - 1){
                    this.setHeadPosition(new int[]{x, 0});
                    //game.getGameData().setGameRunning(false);
                }

                if (y <= 0){
                    this.setHeadPosition(new int[]{x, rows - 1});
                    //game.getGameData().setGameRunning(false);
                }
                break;

            case LEFT:
                x = x - 1;
                this.setHeadPosition(new int[]{x, y});

                if (x >= cols - 1){
                    this.setHeadPosition(new int[]{0, y});
                    //game.getGameData().setGameRunning(false);
                }

                if (x <= 0){
                    this.setHeadPosition(new int[]{cols - 1, y});
                    //game.getGameData().setGameRunning(false);
                }
                break;
            case RIGHT:
                x = x + 1;
                this.setHeadPosition(new int[]{x, y});

                if (x >= cols - 1){
                    this.setHeadPosition(new int[]{0, y});
                    //game.getGameData().setGameRunning(false);
                }

                if (x <= 0){
                    this.setHeadPosition(new int[]{cols - 1, y});
                    //game.getGameData().setGameRunning(false);
                }
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

    /**
     * Method for moving as glutton
     */
    private void moveAsGlutton(){
        int x = 0;
        int y = 0;

        for (int i = 0; i < game.getRows(); i++) {
            for (int j = 0; j < game.getCols(); j++) {
                if (game.getCoordinates()[i][j] == 1){
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        if (game.getSnake2().getHeadPosition()[0] < x){
            setDirection(directions.RIGHT);
            if (getDirection() != directions.LEFT){

            }
        }

        if (game.getSnake2().getHeadPosition()[0] > x){
                setDirection(directions.LEFT);
            if (getDirection() != directions.RIGHT){
            }
        }

        if (game.getSnake2().getHeadPosition()[1] < y){
                setDirection(directions.DOWN);
            if (getDirection() != directions.UP){
            }
        }

        if (game.getSnake2().getHeadPosition()[1] > y){
                setDirection(directions.UP);
            if (getDirection() != directions.DOWN){
            }
        }
    }


}
