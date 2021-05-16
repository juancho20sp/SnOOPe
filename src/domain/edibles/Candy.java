package domain.edibles;

import domain.Game;
import domain.players.SuperPlayer;

import java.awt.*;
import java.io.Serializable;

public class Candy extends Edible implements Serializable {
    /**
     * Constructor of the Candy class
     * @param x The x position of the candy
     * @param y The y position of the candy
     * @param color The color of the candy
     */
    public Candy(int x, int y, Color color) {
        super(x, y, color, -1);

        super.setImage("./images/candy.png");
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
            player.getSnake().decreaseSize(1);
        } else {
            player.getSnake().increaseSize(edible.getPoints());
        }

        // SI LOS COLORES NO SON IGUALES, ENTONCES AFECTAMOS AL CONTRINCANTE
        // SI SON IGUALES, REDUCIMOS UNO

    }
}
