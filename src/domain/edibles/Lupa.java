package domain.edibles;

import java.awt.*;
import java.io.Serializable;

public class Lupa extends Edible implements Serializable {
    public Lupa(int x, int y, Color color) {
        super(x, y, color, 2);

        super.setImage("./images/lupa.png");
    }
}
