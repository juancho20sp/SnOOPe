package domain.edibles;

import domain.Game;
import domain.players.SuperPlayer;

import java.awt.*;
import java.io.Serializable;

public class Apple extends Edible implements Serializable {

    /**
     * Constructor of the Apple class
     * @param x The x position of the apple
     * @param y The y position of the apple
     * @param color The color of the apple
     */
    public Apple(int x, int y, Color color) {
        super(x, y, color, 1);

        super.setImage("./images/apple.png");
    }

    /**
     * Method that overrides the 'eatEdible' method
     */
    @Override
    public void eatEdible(Edible edible, SuperPlayer player, Game game){
        boolean sameColor = false;

        // Add points
        int newPoints = player.getPoints() + edible.getPoints();
        player.setPoints(newPoints);

        // Verify snake color and fruit color
        if (edible.getColor().equals(player.getHeadColor()) || edible.getColor().equals(player.getBodyColor())){
            sameColor = true;
        }

        // Increase size
        if (sameColor){
            System.out.println("SAME COLOR");
            int newUnits = edible.getPoints() + 1;

            player.getSnake().increaseSize(newUnits);
        } else {
            player.getSnake().increaseSize(edible.getPoints());
        }

    }
}
