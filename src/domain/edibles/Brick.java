package domain.edibles;

import java.awt.*;
import java.io.Serializable;

public class Brick extends Edible implements Serializable {
    public Brick(int x, int y, Color color) {
        super(x, y, color, 0);

        super.setImage("./images/brick.png");
    }
}
