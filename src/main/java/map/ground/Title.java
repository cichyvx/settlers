package map.ground;

import map.structures.Structure2D;

import java.awt.*;

public interface Title {

    static Title getTitle(String name, int x, int y){
        if(name.equals(new DefaultTitle(0,0).toString())) return new DefaultTitle(x, y);
        if(name.equals(new GrassTitle(0,0).toString())) return new GrassTitle(x, y);
        if(name.equals(new RockTitle(0,0).toString())) return new RockTitle(x, y);
        if(name.equals(new WaterTitle(0,0).toString())) return new WaterTitle(x, y);
        if(name.equals(new SandTitle(0,0).toString())) return new SandTitle(x, y);
        return null;
    }

    public int getX();
    public int getY();
    public Color getColor();
    public boolean isDestroyable();
    public boolean isWalking();
    public boolean haveObject();
    Structure2D getStructure();
    void addStructure(Structure2D structure);
    void deleteStructure();
    public Rectangle getRectange(int width, int height);
    //public Object getObjectClass();
    //public void setObject(Object object);

}
