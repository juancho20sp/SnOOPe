package domain.snakes;

import domain.Game;
import domain.directions;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Snake extends SuperSnake {
    /**
     * Constructor for the SnakeP1 class
     * @param size The size of the snake
     * @param headPosition The position of the head of the snake
     * @param headColor The head color of the snake
     * @param bodyColor The body color of the snake
     */
    public Snake(int size, int[] headPosition, int[] tailPosition, Color headColor, Color bodyColor, Game game) {
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
        super.setDirection(directions.RIGHT);
    }

    /**
     * Method for moving the snake
     * @param key The keycode of the key pressed
     */

    /**
     * Method for changing positions
     * @param cols The number of cols of the board
     * @param rows The number of rows of the board
     * @return
     */
    /*@Override
    public void updatePositions(int rows, int cols){
        int x = this.getHeadPosition()[0];
        int y = this.getHeadPosition()[1];

        switch (this.getDirection()){
            case UP:
                y = y-1;
                this.setHeadPosition(new int[]{x, y});

                if (y >= rows - 1){
                    this.setHeadPosition(new int[]{x, 0});
                    //gameData.setGameRunning(false);
                }

                if (y <= 0){
                    this.setHeadPosition(new int[]{x, rows - 1});
                    //gameData.setGameRunning(false);
                }
                break;

            case DOWN:
                y = y + 1;
                this.setHeadPosition(new int[]{x, y});

                if (y >= rows - 1){
                    this.setHeadPosition(new int[]{x, 0});
                    //gameData.setGameRunning(false);
                }

                if (y <= 0){
                    this.setHeadPosition(new int[]{x, rows - 1});
                    //gameData.setGameRunning(false);
                }
                break;

            case LEFT:
                x = x - 1;
                this.setHeadPosition(new int[]{x, y});

                if (x >= cols - 1){
                    this.setHeadPosition(new int[]{0, y});
                    //gameData.setGameRunning(false);
                }

                if (x <= 0){
                    this.setHeadPosition(new int[]{cols - 1, y});
                    //gameData.setGameRunning(false);
                }
                break;
            case RIGHT:
                x = x + 1;
                this.setHeadPosition(new int[]{x, y});

                if (x >= cols - 1){
                    this.setHeadPosition(new int[]{0, y});
                    //gameData.setGameRunning(false);
                }

                if (x <= 0){
                    this.setHeadPosition(new int[]{cols - 1, y});
                    //gameData.setGameRunning(false);
                }
                break;

        }
    }*/
}
