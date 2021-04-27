package domain.snakes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
    private int size = 5;
    private int[] headPosition;
    private ArrayList<int[]> positions;
    private Color headColor;
    private Color bodyColor;
    private int direction = KeyEvent.VK_RIGHT;
    private int frequency = 30;

    public Snake(int size, int[] headPosition, Color headColor, Color bodyColor) {
        this.size = size;
        this.headPosition = headPosition;
        this.headColor = headColor;
        this.bodyColor = bodyColor;

        this.positions = new ArrayList<>();
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
        this.headPosition = headPosition;
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
