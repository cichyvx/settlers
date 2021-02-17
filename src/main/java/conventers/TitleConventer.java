package conventers;

import map.ground.Title;
import map.structures.Structure2D;

/**
 * the class is used to convert Titles to one typo in order to save them,
 * among others, to a JSON file, or to convert them from the current class
 * to the state before conversion
 */


public class TitleConventer {
    private int x,y;
    private String name, structure;

    /**
     * turns any animal into a safe write-ready type
     * @param x horizontal position of the field
     * @param y vertical position of the field
     * @param name name of the field
     * @param WIDTH width of the field
     * @param HEIGHT height of the field
     * @param autor author of the entire map
     * @param description description of the entire map
     * @param structure the structure in the field
     */
    public TitleConventer(int x, int y, String name, int WIDTH, int HEIGHT, String autor, String description, String structure){
        this.x = x;
        this.y = y;
        this.name = name;
        this.structure = structure;
    }

    /**
     * turns any animal into a safe write-ready type
     * @param title to be converted
     */
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

    /**
     *
     * @param titleConventer safe type to be converted
     * @return original type before converting
     */

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
