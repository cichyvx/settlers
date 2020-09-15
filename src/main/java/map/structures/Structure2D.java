package map.structures;

public interface Structure2D {
    int TREE = 1;

    static int getStructuresCount(){
        return 1;
    }

    static Structure2D getStructure(String name){
        if(name.equals(new TreeStructure().toString())) return new TreeStructure();
        return null;
    }

    static Structure2D getStructure(int id){
        if(id == TREE) return new TreeStructure();
        return null;
    }
}
