package domain.edibles;

import java.awt.*;
import java.io.Serializable;

public class BadArrow extends Edible implements Serializable {
    public BadArrow(int x, int y, Color color) {
        super(x, y, color, 2);

        super.setImage("./images/badArrow.png");
    }
}
