package domain.edibles;

import java.awt.*;
import java.io.Serializable;

public class Apple extends Edible implements Serializable {
    // domain.edibles.Apple
    public Apple(int x, int y, Color color) {
        super(x, y, color, 1);

        super.setImage("./images/apple.png");
    }
}
