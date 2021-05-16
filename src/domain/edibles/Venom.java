package domain.edibles;

import java.awt.*;
import java.io.Serializable;

public class Venom extends Edible implements Serializable {
    public Venom(int x, int y, Color color) {
        super(x, y, color, 0);

        super.setImage("./images/skull.png");
    }
}
