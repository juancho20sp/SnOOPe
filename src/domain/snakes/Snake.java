package domain.snakes;

import domain.GameData;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class Snake {
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

        this.gameData = gameData;
    }

    /**
     * Method for moving the snake
     * @param key The keycode of the key pressed
     */
    public abstract void move(int key);

    /**
     * Method for changing positions
     * @param cols The number of cols of the board
     * @param rows The number of rows of the board
     * @return
     */
    public abstract void updatePositions(int rows, int cols);


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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


}
