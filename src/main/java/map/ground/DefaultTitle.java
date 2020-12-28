package map.ground;

import debuger.SettlerDebuger;
import map.structures.Structure2D;

import java.awt.*;

public class DefaultTitle implements Title, SettlerDebuger {

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

    @Override
    public String getCornerText() {
        StringBuffer debugText = new StringBuffer();
        debugText.append("position: x: " + x + " y: " + y + System.lineSeparator());
        debugText.append("Size: " + 25 + " x " + 25 + System.lineSeparator());
        debugText.append("Type: " + toString() + System.lineSeparator());
        debugText.append("destroyable: " + (isDestroyable()? "yes" : "no") + System.lineSeparator());
        debugText.append("walkable: " + (isDestroyable()? "yes" : "no") + System.lineSeparator());
        debugText.append("structure: " + (haveObject()? structure.getCornerText() : "no") + System.lineSeparator());
        return debugText.toString();
    }
}
