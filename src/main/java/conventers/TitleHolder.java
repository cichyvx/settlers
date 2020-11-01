package conventers;

import java.util.ArrayList;
import java.util.List;

public class TitleHolder {
    public final String name, autor, description;
    public final int width, height;
    private List<TitleConventer> titleList;
    private List<AnimalConventer> animallist;

    public TitleHolder(int width, int height, String name, String autor, String description){
        this.name = name;
        this.autor = autor;
        this.description = description;
        this.width = width;
        this.height = height;
        titleList  = new ArrayList<>();
        animallist = new ArrayList<>();
    }

    public void addTitle(TitleConventer titleConventer){
        titleList.add(titleConventer);
    }

    public void addAnimal(AnimalConventer animalConventer) { animallist.add(animalConventer); }

    public List<TitleConventer> getTitles(){
        return titleList;
    }

    public List<AnimalConventer> getAnimals() { return animallist; }

}
