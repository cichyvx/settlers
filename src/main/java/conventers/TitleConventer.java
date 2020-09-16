package conventers;

import map.ground.Title;

public class TitleConventer {
    private int x,y;
    private String name;

    public TitleConventer(int x, int y, String name, int WIDTH, int HEIGHT, String autor, String description){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public TitleConventer(Title title){
        this.x = title.getX();
        this.y = title.getY();
        this.name = title.toString();
    }

    public TitleConventer(){

    }

    public static Title getTitle(TitleConventer titleConventer){
        return Title.getTitle(titleConventer.getName(), titleConventer.getX(), titleConventer.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }
}
