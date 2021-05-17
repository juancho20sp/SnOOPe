package domain.snakes;

import domain.Game;
import domain.GameData;
import domain.directions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class SuperSnake implements Serializable {
    private int size = 3;
    private int[] headPosition;
    private ArrayList<int[]> positions;
    private Color headColor;
    private Color bodyColor;
    private directions direction = directions.RIGHT;
    private int frequency = 80;

    // Game
    protected Game game;

    public SuperSnake(int size, int[] headPosition, Color headColor, Color bodyColor, Game game) {
        this.size = size;
        this.headPosition = headPosition;
        this.headColor = headColor;
        this.bodyColor = bodyColor;

        this.positions = new ArrayList<>();

        this.game = game;
    }

    /**
     * Abstract method for moving the snake
     */
    public void move(){}

    /**
     * Method for moving the snake
     * @param key The keycode of the key pressed
     */
    public void move(directions key){
        switch (key){
            case UP:
                this.setDirection(directions.UP);
                // System.out.println("UP");
                /*if (super.getDirection() != KeyEvent.VK_DOWN){
                }*/
                break;
            case DOWN:
                this.setDirection(directions.DOWN);
                // System.out.println("DOWN");
                /*if (super.getDirection() != KeyEvent.VK_UP){
                }*/
                break;
            case LEFT:
                this.setDirection(directions.LEFT);
                //System.out.println("LEFT");
                /*if (super.getDirection() != KeyEvent.VK_RIGHT){
                }*/
                break;
            case RIGHT:
                this.setDirection(directions.RIGHT);
                //System.out.println("RIGHT");
                /*if (super.getDirection() != KeyEvent.VK_LEFT){
                }*/
                break;
        }
    }

    /**
     * Method for changing positions
     * @param cols The number of cols of the board
     * @param rows The number of rows of the board
     * @return
     */
    public void updatePositions(int rows, int cols){
        int x = this.getHeadPosition()[0];
        int y = this.getHeadPosition()[1];

        boolean infiniteBoard = game.getGameData().isBoardInfinite();

        switch (this.getDirection()){
            case UP:
                y = y-1;
                this.setHeadPosition(new int[]{x, y});

                if (y >= rows - 1){
                    if (infiniteBoard){
                        this.setHeadPosition(new int[]{x, 0});
                    } else {
                        game.getGameData().setGameRunning(false);
                    }
                }

                if (y <= 0){
                    if (infiniteBoard){
                        this.setHeadPosition(new int[]{x, rows - 1});
                    } else {
                        game.getGameData().setGameRunning(false);
                    }
                }
                break;

            case DOWN:
                y = y + 1;
                this.setHeadPosition(new int[]{x, y});

                if (y >= rows - 1){
                    if (infiniteBoard){
                        this.setHeadPosition(new int[]{x, 0});
                    } else {
                        game.getGameData().setGameRunning(false);
                    }
                }

                if (y <= 0){
                    if (infiniteBoard){
                        this.setHeadPosition(new int[]{x, rows - 1});
                    } else {
                        game.getGameData().setGameRunning(false);
                    }
                }
                break;

            case LEFT:
                x = x - 1;
                this.setHeadPosition(new int[]{x, y});

                if (x >= cols - 1){
                    if (infiniteBoard){
                        this.setHeadPosition(new int[]{0, y});
                    } else {
                        game.getGameData().setGameRunning(false);
                    }
                }

                if (x <= 0){
                    if (infiniteBoard){
                        this.setHeadPosition(new int[]{cols - 1, y});
                    } else {
                        game.getGameData().setGameRunning(false);
                    }
                }
                break;
            case RIGHT:
                x = x + 1;
                this.setHeadPosition(new int[]{x, y});

                if (x >= cols - 1){
                    if (infiniteBoard){
                        this.setHeadPosition(new int[]{0, y});
                    } else {
                        game.getGameData().setGameRunning(false);
                    }
                }

                if (x <= 0){
                    if (infiniteBoard){
                        this.setHeadPosition(new int[]{cols - 1, y});
                    } else {
                        game.getGameData().setGameRunning(false);
                    }
                }
                break;

        }
    }


    /**
     * Method for increasing the size of the snake
     * @param size The number of units to increase
     */
    public void increaseSize(int size){
        // Increase size
        for (int i = 0; i < size; i++){
            int[] lastPosition = new int[2];

            if (this.positions.size() == 0){
                lastPosition = this.getHeadPosition();
                lastPosition = new int[]{lastPosition[0] - 1, lastPosition[1] - 1};
            } else {
                lastPosition = this.positions.get(this.positions.size() - 1);
            }
            this.positions.add(lastPosition);
        }

        // Verify speed
        if ( this.positions.size() % 5 == 0){
            this.setFrequency(this.getFrequency() + this.game.getGameData().getFrequencyValue());

            System.out.println("New frec: " + this.getFrequency());
        }

        System.out.println("New size: " + this.positions.size());
    }

    /**
     * Method for decreasing the size of the snake
     * @param size The number of units to decrease
     */
    public void decreaseSize(int size){
        if (this.positions.size() == 0 && size > 0){
            System.out.println("GAME OVER POR TAMAÑO");
            this.game.getGameData().setGameRunning(false);
        }

        if (this.positions.size() == 0){

        }

        // Decrease size
        for (int i = 0; i < size; i++){
            try {
                this.positions.remove(0);
            } catch(IndexOutOfBoundsException ex) {

            }



        }

        // Verify speed
        if ( this.positions.size() % 5 == 0){
            this.setFrequency(this.getFrequency() - this.game.getGameData().getFrequencyValue());
            System.out.println("New frec: " + this.getFrequency());
        }
    }

    /**
     * Method for adding new blocks to the body of the snake
     */
    public void addPosition(int[] position) {
        this.positions.add(position);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[] getHeadPosition() {
        return headPosition;
    }

    public void setHeadPosition(int[] headPosition) {
        // The first part of the body will be where the head was
        this.positions.add(this.headPosition);

        // Reset the head
        this.headPosition = headPosition;

        // Delete the last element of the snake body
        this.positions.remove(0);
    }

    public Color getHeadColor() {
        return headColor;
    }

    public void setHeadColor(Color headColor) {
        this.headColor = headColor;
    }

    public Color getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(Color bodyColor) {
        this.bodyColor = bodyColor;
    }

    public ArrayList<int[]> getPositions() {
        return positions;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public directions getDirection() {
        return direction;
    }

    public void setDirection(directions direction) {
        this.direction = direction;
    }

}
