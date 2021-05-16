package domain.edibles;

import domain.Game;
import domain.players.SuperPlayer;

import java.awt.*;
import java.io.Serializable;

public class Rainbow extends Edible implements Serializable {
    /**
     * Constructor of the Rainbow class
     * @param x The x position of the rainbow apple
     * @param y The y position of the rainbow apple
     * @param color The color of the rainbow apple
     */
    public Rainbow(int x, int y, Color color) {
        super(x, y, color, 3);

        super.setImage("./images/arcoiris.png");
    }

    /**
     * Method that overrides the 'eatEdible' method
     */
    /*@Override
    public void eatEdible(Edible edible, SuperPlayer player, Game game){
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

    }*/
}
