package map.structures;

public interface Structure2D {
    int TREE = 1;
    int PLANT = 2;

    static int getStructuresCount(){
        return 1;
    }

    static Structure2D getStructure(String name){
        if(name.equals(new TreeStructure().toString())) return new TreeStructure();
        if(name.equals(new PlantStructure().toString())) return new PlantStructure();
        return null;
    }

    static Structure2D getStructure(int id){
        if(id == TREE) return new TreeStructure();
        if(id == PLANT) return new PlantStructure();
        return null;
    }
}
