package domain.edibles;

import domain.Game;
import domain.players.SuperPlayer;

import java.awt.*;
import java.io.Serializable;

public class Edible implements Serializable {
    private int x;
    private int y;
    private Color color;
    private String type;
    protected String image;
    private int points;

    public Edible(int x, int y, Color color, int points) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.points = points;
    }

    /**
     * Method for eating an apple
     */
    public void eatEdible(Edible edible, SuperPlayer player, Game game){
        // Add points
        int newPoints = player.getPoints() + edible.getPoints();
        player.setPoints(newPoints);

        // Clear board
        game.getBoard().cleanPosition(edible.getX(), edible.getY());

        // Increase size
        player.getSnake().increaseSize(edible.getPoints());
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

    public String getType() {
        return type;
    }

    public void setType(String tipo) {
        this.type = tipo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
