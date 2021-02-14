package map.structures;

import debuger.SettlerDebuger;

public interface Structure2D extends SettlerDebuger {
    int TREE = 1;
    int PLANT = 2;
    int BERRIES = 3;

    static int getStructuresCount(){
        return 3;
    }

    static Structure2D getStructure(String name){
        if(name.equals(new TreeStructure().toString())) return new TreeStructure();
        if(name.equals(new PlantStructure().toString())) return new PlantStructure();
        if(name.equals(new BerriesStructure().toString())) return new BerriesStructure();
        return null;
    }

    static Structure2D getStructure(int id){
        if(id == TREE) return new TreeStructure();
        if(id == PLANT) return new PlantStructure();
        if(id == BERRIES) return new BerriesStructure();
        return null;
    }
}
