package map.ground;

import java.awt.*;

public class SandTitle extends DefaultTitle{

    public SandTitle(int x, int y) {
        super(x, y);
    }

    @Override
    public Color getColor() {
        return Color.yellow;
    }

    @Override
    public String toString() {
        return "SAND";
    }

    @Override
    public boolean isWalking() {
        return true;
    }
}
