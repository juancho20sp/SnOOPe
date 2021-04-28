package domain.edibles;

import java.awt.*;

public class Edible {
    private int x;
    private int y;
    private Color color;
    private int points;

    public Edible(int x, int y, Color color, int points) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.points = points;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
