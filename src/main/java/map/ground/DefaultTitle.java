package map.ground;

import java.awt.*;

public class DefaultTitle implements Title{

    private final int x,y;

    public DefaultTitle(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Color getColor() {
        return Color.darkGray;
    }

    @Override
    public String toString() {
        return "DEFAULT_TITLE";
    }

    @Override
    public boolean isDestroyable() {
        return false;
    }

    @Override
    public boolean isWalking() {
        return false;
    }

    @Override
    public boolean haveObject() {
        return false;
    }

    @Override
    public Rectangle getRectange(int width, int height) {
        return new Rectangle(x, y, width, height);
    }
}
