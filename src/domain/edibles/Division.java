package domain.edibles;

import java.awt.*;
import java.io.Serializable;

public class Division extends Edible implements Serializable {
    public Division(int x, int y, Color color) {
        super(x, y, color, 2);

        super.setImage("./images/division.png");
    }
}
