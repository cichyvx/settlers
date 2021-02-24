package map.structures;

import debuger.SettlerDebuger;

public interface Structure2D extends SettlerDebuger {
    int TREE = 1;
    int PLANT = 2;
    int BERRIES = 3;
    int STONE = 4;
    int WATERLILY = 5;
    int CACTUS = 6;

    static int getStructuresCount(){
        return 6;
    }

    static Structure2D getStructure(String name){
        if(name.equals(new TreeStructure().toString())) return new TreeStructure();
        if(name.equals(new PlantStructure().toString())) return new PlantStructure();
        if(name.equals(new BerriesStructure().toString())) return new BerriesStructure();
        if(name.equals(new StoneStructure().toString())) return new StoneStructure();
        if(name.equals(new WaterLilyStructure().toString())) return new WaterLilyStructure();
        if(name.equals(new CactusStructure().toString())) return new CactusStructure();

        return null;
    }

    static Structure2D getStructure(int id){
        if(id == TREE) return new TreeStructure();
        if(id == PLANT) return new PlantStructure();
        if(id == BERRIES) return new BerriesStructure();
        if(id == STONE) return new StoneStructure();
        if(id == WATERLILY) return new WaterLilyStructure();
        if(id == CACTUS) return new CactusStructure();
        return null;
    }
}
