package domain.players;

import java.awt.*;

public abstract class Player {
    private String name;
    private Color headColor;
    private Color bodyColor;
    private int points = 0;
    private int surprises = 0;
    private int number;

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
}
