package domain.edibles;

import java.awt.*;
import java.io.Serializable;

public class Rainbow extends Edible implements Serializable {
    public Rainbow(int x, int y, Color color) {
        super(x, y, color, 3);

        super.setImage("./images/arcoiris.png");
    }
}
