package domain.snakes;

import domain.Game;
import domain.directions;
import java.awt.*;
import java.io.Serializable;


public class Snake extends SuperSnake implements Serializable {
    /**
     * Constructor for the SnakeP1 class
     * @param size The size of the snake
     * @param headPosition The position of the head of the snake
     * @param headColor The head color of the snake
     * @param bodyColor The body color of the snake
     */
    public Snake(int size, int[] headPosition, int[] tailPosition, Color headColor, Color bodyColor, Game game) {
        super(size, headPosition, headColor, bodyColor, game);

        // Snake positions
        super.setHeadPosition(headPosition);
        // Está mirando a la derecha
        if (headPosition[0] > tailPosition[0]){
            for (int i = 1; i <= size - 1; i++) {
                super.addPosition(new int[]{headPosition[0] - i, headPosition[1]});
            }
        } else {
            for (int i = 1; i <= size - 1; i++) {
                super.addPosition(new int[]{headPosition[0] + i, headPosition[1]});
            }
        }


        // Snake direction
        super.setDirection(directions.RIGHT);
    }
}
