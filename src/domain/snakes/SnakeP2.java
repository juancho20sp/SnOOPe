package domain.snakes;

import domain.GameData;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SnakeP2 extends Snake{
    /**
     * Constructor for the SnakeP1 class
     * @param size The size of the snake
     * @param headPosition The position of the head of the snake
     * @param headColor The head color of the snake
     * @param bodyColor The body color of the snake
     * @param gameData An instance of the game data class
     */
    public SnakeP2(int size, int[] headPosition, Color headColor, Color bodyColor, GameData gameData) {
        super(size, headPosition, headColor, bodyColor, gameData);

        // Insertamos la serpiente
        super.setHeadPosition(headPosition);
        super.addPosition(new int[]{headPosition[0] + 1, headPosition[1]});
        super.addPosition(new int[]{headPosition[0] + 2, headPosition[1]});

        // Definimos la dirección
        super.setDirection(KeyEvent.VK_A);
    }

    /**
     * Method for moving the snake
     * @param key The keycode of the key pressed
     */
    @Override
    public void move(int key){
        switch (key){
            case KeyEvent.VK_W:
                // System.out.println("UP");
                if (super.getDirection() != KeyEvent.VK_S){
                    super.setDirection(KeyEvent.VK_W);
                }
                break;
            case KeyEvent.VK_S:
                // System.out.println("DOWN");
                if (super.getDirection() != KeyEvent.VK_W){
                    super.setDirection(KeyEvent.VK_S);
                }
                break;
            case KeyEvent.VK_A:
                //System.out.println("LEFT");
                if (super.getDirection() != KeyEvent.VK_D){
                    super.setDirection(KeyEvent.VK_A);
                }
                break;
            case KeyEvent.VK_D:
                //System.out.println("RIGHT");
                if (super.getDirection() != KeyEvent.VK_A){
                    super.setDirection(KeyEvent.VK_D);
                }
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
        int x = super.getHeadPosition()[0];
        int y = super.getHeadPosition()[1];

        switch (this.getDirection()){
            case KeyEvent.VK_W:
                y = y-1;
                super.setHeadPosition(new int[]{x, y});

                if (y >= rows - 1){
                    super.setHeadPosition(new int[]{x, 0});
                    //gameData.setGameRunning(false);
                }

                if (y <= 0){
                    super.setHeadPosition(new int[]{x, rows - 1});
                    //gameData.setGameRunning(false);
                }
                break;

            case KeyEvent.VK_S:
                y = y + 1;
                super.setHeadPosition(new int[]{x, y});

                if (y >= rows - 1){
                    super.setHeadPosition(new int[]{x, 0});
                    //gameData.setGameRunning(false);
                }

                if (y <= 0){
                    super.setHeadPosition(new int[]{x, rows - 1});
                    //gameData.setGameRunning(false);
                }
                break;

            case KeyEvent.VK_A:
                x = x - 1;
                super.setHeadPosition(new int[]{x, y});

                if (x >= cols - 1){
                    super.setHeadPosition(new int[]{0, y});
                    //gameData.setGameRunning(false);
                }

                if (x <= 0){
                    super.setHeadPosition(new int[]{cols - 1, y});
                    //gameData.setGameRunning(false);
                }
                break;
            case KeyEvent.VK_D:
                x = x + 1;
                super.setHeadPosition(new int[]{x, y});

                if (x >= cols - 1){
                    super.setHeadPosition(new int[]{0, y});
                    //gameData.setGameRunning(false);
                }

                if (x <= 0){
                    super.setHeadPosition(new int[]{cols - 1, y});
                    //gameData.setGameRunning(false);
                }
                break;

        }
    }
}
