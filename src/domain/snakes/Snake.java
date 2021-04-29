package domain.snakes;

import domain.GameData;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
    private int size = 3;
    private int[] headPosition;
    private ArrayList<int[]> positions;
    private Color headColor;
    private Color bodyColor;
    private int direction = KeyEvent.VK_RIGHT;
    private int frequency = 80;

    // Game data
    private GameData gameData;

    public Snake(int size, int[] headPosition, Color headColor, Color bodyColor, GameData gameData) {
        this.size = size;
        this.headPosition = headPosition;
        this.headColor = headColor;
        this.bodyColor = bodyColor;

        this.positions = new ArrayList<>();

        this.headPosition = new int[]{2, 0};
       // this.positions.add(this.headPosition);
        this.positions.add(new int[]{1,0});
        this.positions.add(new int[]{0,0});

        this.gameData = gameData;
    }

    /**
     * Method for moving the snake
     * @param key The keycode of the key pressed
     */
    public void move(int key){
        switch (key){
            case KeyEvent.VK_UP:
               // System.out.println("UP");
                if (this.direction != KeyEvent.VK_DOWN){
                    this.setDirection(KeyEvent.VK_UP);
                }
                break;
            case KeyEvent.VK_DOWN:
               // System.out.println("DOWN");
                if (this.direction != KeyEvent.VK_UP){
                    this.setDirection(KeyEvent.VK_DOWN);
                }
                break;
            case KeyEvent.VK_LEFT:
                //System.out.println("LEFT");
                if (this.direction != KeyEvent.VK_RIGHT){
                    this.setDirection(KeyEvent.VK_LEFT);
                }
                break;
            case KeyEvent.VK_RIGHT:
                //System.out.println("RIGHT");
                if (this.direction != KeyEvent.VK_LEFT){
                    this.setDirection(KeyEvent.VK_RIGHT);
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
    public void updatePositions(int rows, int cols){
        int x = this.getHeadPosition()[0];
        int y = this.getHeadPosition()[1];

        switch (this.getDirection()){
            case KeyEvent.VK_UP:
                y = y-1;
                this.setHeadPosition(new int[]{x, y});

                if (y >= rows - 1){
                    //this.setHeadPosition(new int[]{x, 0});
                    gameData.setGameRunning(false);
                }

                if (y <= 0){
                    //this.setHeadPosition(new int[]{x, rows - 1});
                    gameData.setGameRunning(false);
                }
                break;

            case KeyEvent.VK_DOWN:
                y = y + 1;
                this.setHeadPosition(new int[]{x, y});

                if (y >= rows - 1){
                    //this.setHeadPosition(new int[]{x, 0});
                    gameData.setGameRunning(false);
                }

                if (y <= 0){
                    //this.setHeadPosition(new int[]{x, rows - 1});
                    gameData.setGameRunning(false);
                }
                break;

            case KeyEvent.VK_LEFT:
                x = x - 1;
                this.setHeadPosition(new int[]{x, y});

                if (x >= cols - 1){
                    //this.setHeadPosition(new int[]{0, y});
                    gameData.setGameRunning(false);
                }

                if (x <= 0){
                    //this.setHeadPosition(new int[]{cols - 1, y});
                    gameData.setGameRunning(false);
                }
                break;
            case KeyEvent.VK_RIGHT:
                x = x + 1;
                this.setHeadPosition(new int[]{x, y});

                if (x >= cols - 1){
                    //this.setHeadPosition(new int[]{0, y});
                    gameData.setGameRunning(false);
                }

                if (x <= 0){
                    //this.setHeadPosition(new int[]{cols - 1, y});
                    gameData.setGameRunning(false);
                }
                break;

        }
    }

    /**
     * Method for increasing the size of the snake
     * @param size The number of units to increase
     */
    public void increaseSize(int size){
        for (int i = 0; i < size; i++){
            int[] lastPosition = this.positions.get(this.positions.size() - 1);
            this.positions.add(lastPosition);
        }
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }


}
