package domain.players;

import domain.Game;
import domain.snakes.SuperSnake;
import domain.snakes.Snake;

import java.awt.*;

public abstract class SuperPlayer {
    // Player data
    private String name;

    // Snake
    private Color headColor;
    private Color bodyColor;
    private SuperSnake superSnake;

    private int points = 0;
    private int surprises = 0;
    private int number;

    /**
     * Method for creating a new snake
     */
    public void createSnake(int[] headPos, int[] tailPos, Game game){
        this.setSnake(new Snake(3, headPos, tailPos, this.headColor, this.bodyColor, game));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSurprises() {
        return surprises;
    }

    public void setSurprises(int surprises) {
        this.surprises = surprises;
    }

    public SuperSnake getSnake() {
        return superSnake;
    }

    public void setSnake(SuperSnake superSnake) {
        this.superSnake = superSnake;
    }
}
