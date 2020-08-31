package map.ground;

import java.awt.*;

public interface Title {

    public int getId();
    public int getX();
    public int getY();
    public Color getColor();
    public boolean isDestroyable();
    public boolean isWalking();
    public boolean haveObject();
    public Rectangle getRectange(int width, int height);
    //public Object getObjectClass();
    //public void setObject(Object object);

}
