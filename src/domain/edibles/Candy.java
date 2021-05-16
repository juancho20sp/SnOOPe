package domain.edibles;

import java.awt.*;
import java.io.Serializable;

public class Candy extends Edible implements Serializable {
    public Candy(int x, int y, Color color) {
        super(x, y, color, -1);

        super.setImage("./images/candy.png");
    }
}
