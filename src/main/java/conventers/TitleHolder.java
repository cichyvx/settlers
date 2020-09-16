package conventers;

import java.util.ArrayList;
import java.util.List;

public class TitleHolder {
    public final String name, autor, description;
    public final int width, height;
    private List<TitleConventer> list;

    public TitleHolder(int width, int height, String name, String autor, String description){
        this.name = name;
        this.autor = autor;
        this.description = description;
        this.width = width;
        this.height = height;
        list  = new ArrayList<>();
    }

    public void add(TitleConventer titleConventer){
        list.add(titleConventer);
    }

    public List<TitleConventer> getList(){
        return list;
    }

}
