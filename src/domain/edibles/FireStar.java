package domain.edibles;

import java.awt.*;
import java.io.Serializable;

public class FireStar extends Edible implements Serializable {
    public FireStar(int x, int y, Color color) {
        super(x, y, color, 10);

        super.setImage("./images/start.png");
    }
}
