package domain.edibles;

import java.awt.*;
import java.io.Serializable;

public class Fruit extends Edible implements Serializable {
    public Fruit(int x, int y, Color color, int points) {
        super(x, y, color, points);
    }


}
