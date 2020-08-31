package map.ground;

import java.awt.*;

public class RockTitle extends DefaultTitle{

    public RockTitle(int x, int y) {
        super(x, y);
    }

    @Override
    public Color getColor() {
        return Color.gray;
    }

    @Override
    public String toString() {
        return "ROCK";
    }

    @Override
    public boolean isWalking() {
        return true;
    }
}
