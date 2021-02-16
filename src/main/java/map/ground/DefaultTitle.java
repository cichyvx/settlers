package map.ground;

import debuger.SettlerDebuger;
import map.structures.Structure2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DefaultTitle implements Title, SettlerDebuger {

    private final int x,y;
    Structure2D structure;

    public DefaultTitle(int x, int y) {
        this.x = x;
        this.y = y;
        structure = null;
    }

    private ArrayList<Integer> avalibeStruct() {
        ArrayList<Integer> list = new ArrayList<>();
        Class c = this.getClass();
        if(c.equals(DefaultTitle.class)){
            //do nothing
        }
        else if(c.equals(GrassTitle.class)){
            list.add(Structure2D.TREE);
            list.add(Structure2D.PLANT);
            list.add(Structure2D.BERRIES);
        }
        else if(c.equals(RockTitle.class)){
            //do nothing
        }
        else if(c.equals(SandTitle.class)){
            //do nothing
        }
        else if(c.equals(WaterTitle.class)){
            //do nothing
        }
        return list;
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
    public void generateRandomStructure() {
        ArrayList<Integer> list = avalibeStruct();

        if(list.size() == 0)
            return;

        Random r = new Random();
        int rand = r.nextInt(list.size() + 1);
        structure = Structure2D.getStructure(rand);
    }

    @Override
    public String getCornerText() {
        StringBuilder debugText = new StringBuilder();

        debugText.append("position: x: ")
                .append(x).append(" y: ")
                .append(y).append(System.lineSeparator());

        debugText.append("Size: " + 25 + " x " + 25)
                .append(System.lineSeparator());

        debugText.append("Type: ")
                .append(toString())
                .append(System.lineSeparator());

        debugText.append("destroyable: ")
                .append(isDestroyable() ? "yes" : "no")
                .append(System.lineSeparator());

        debugText.append("walkable: ")
                .append(isDestroyable() ? "yes" : "no")
                .append(System.lineSeparator());

        debugText.append("structure: ")
                .append(haveObject() ? structure.getCornerText() : "no")
                .append(System.lineSeparator());

        return debugText.toString();
    }
}
