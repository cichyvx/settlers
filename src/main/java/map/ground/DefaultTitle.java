package map.ground;

import map.structures.Structure2D;

import java.awt.*;

public class DefaultTitle implements Title{

    private final int x,y;
    Structure2D structure;

    public DefaultTitle(int x, int y) {
        this.x = x;
        this.y = y;
        structure = null;
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
        return this.structure != null;
    }

    @Override
    public Structure2D getStructure() {
        return this.structure;
    }

    @Override
    public void addStructure(Structure2D structure) {
        if(this.structure != null) return;
        this.structure = structure;
    }

    @Override
    public void deleteStructure() {
        this.structure = null;
    }

    @Override
    public Rectangle getRectange(int width, int height) {
        return new Rectangle(x, y, width, height);
    }
}
