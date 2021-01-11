package map.ground;

import java.awt.*;

public class GrassTitle extends DefaultTitle{

    public GrassTitle(int x, int y) {
        super(x, y);
    }

    @Override
    public Color getColor() {
        return Color.green;
    }

    @Override
    public String toString() {
        return "GRASS";
    }

    @Override
    public boolean isWalking() {
        return true;
    }
}
