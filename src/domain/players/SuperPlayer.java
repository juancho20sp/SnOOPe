package domain.players;

import domain.Game;
import domain.edibles.Edible;
import domain.edibles.PowerUp;
import domain.snakes.SuperSnake;
import domain.snakes.Snake;

import java.awt.*;
import java.io.Serializable;

public abstract class SuperPlayer implements Serializable {
    // Player data
    private String name;

    // Snake
    private Color headColor;
    private Color bodyColor;
    private SuperSnake superSnake;

    // Power ups
    private PowerUp powerUp;

    private int points = 0;
    private int surprises = 0;
    private int number;

    /**
     * Method for creating a new snake
     */
    public void createSnake(int[] headPos, int[] tailPos, Game game){
        this.setSnake(new Snake(3, headPos, tailPos, this.headColor, this.bodyColor, game));
    }

    /**
     * Method for using the power up
     * @return
     */
    public void usePowerUp() {
        if (this.getPowerUp() != null){
            this.getPowerUp().usePowerUp(this);
        }
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

    public PowerUp getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }
}
