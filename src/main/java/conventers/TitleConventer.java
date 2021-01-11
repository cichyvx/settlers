package conventers;

import map.ground.Title;
import map.structures.Structure2D;

public class TitleConventer {
    private int x,y;
    private String name, structure;

    public TitleConventer(int x, int y, String name, int WIDTH, int HEIGHT, String autor, String description, String structure){
        this.x = x;
        this.y = y;
        this.name = name;
        this.structure = structure;
    }

    public TitleConventer(Title title){
        this.x = title.getX();
        this.y = title.getY();
        this.name = title.toString();
        if(title.haveObject())
            this.structure = title.getStructure().toString();
        else
            this.structure = "";
    }

    public TitleConventer(){

    }

    public static Title getTitle(TitleConventer titleConventer){
        if(titleConventer.getStructure() == null)
            return Title.getTitle(titleConventer.getName(), titleConventer.getX(), titleConventer.getY());

        Title title = Title.getTitle(titleConventer.getName(), titleConventer.getX(), titleConventer.getY());
        title.addStructure(Structure2D.getStructure(titleConventer.getStructure()));
        return title;
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

    public String getStructure() { return structure; }
}
