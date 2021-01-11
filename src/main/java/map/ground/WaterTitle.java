package map.ground;

import java.awt.*;

public class WaterTitle extends DefaultTitle{

    public WaterTitle(int x, int y) {
        super(x, y);
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public String toString() {
        return "WATER";
    }
}
