package map.ground;

import map.structures.Structure2D;

import java.awt.*;

public interface Title {

    int DEFAULT = 1;
    int GRASS = 2;
    int ROCK = 3;
    int WATER = 4;
    int SAND = 5;

    static int getTitleCount(){
        return 5;
    }

    static Title getTitle(int id, int x, int y){
        if(id == DEFAULT) return new DefaultTitle(x, y);
        if(id == GRASS) return new GrassTitle(x, y);
        if(id == ROCK) return new RockTitle(x, y);
        if(id == WATER) return new WaterTitle(x, y);
        if(id == SAND) return new SandTitle(x, y);

        return null;
    }

    static Title getTitle(String name, int x, int y){
        if(name.equals(new DefaultTitle(0,0).toString())) return new DefaultTitle(x, y);
        if(name.equals(new GrassTitle(0,0).toString())) return new GrassTitle(x, y);
        if(name.equals(new RockTitle(0,0).toString())) return new RockTitle(x, y);
        if(name.equals(new WaterTitle(0,0).toString())) return new WaterTitle(x, y);
        if(name.equals(new SandTitle(0,0).toString())) return new SandTitle(x, y);
        return null;
    }

    static Title[] getTitles(int x, int y){
        return new Title[] {new DefaultTitle(x,y),
        new GrassTitle(x,y),
        new RockTitle(x, y),
        new SandTitle(x, y),
        new WaterTitle(x, y)};
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
