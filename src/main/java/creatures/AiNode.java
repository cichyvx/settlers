package creatures;

import map.ground.Title;
import map.structures.Structure2D;

import java.util.ArrayList;
import java.util.List;

public class AiNode<T extends Structure2D & Title> {
    public final AiNode previous;
    public final int i,j;
    public final T type;


    public AiNode(int i, int j, AiNode previous, T type){
        this.i = i;
        this.j = j;
        this.previous = previous;
        this.type = type;
    }

    public AiNode(int i, int j, T type){
        this.i = i;
        this.j = j;
        this.previous = null;
        this.type = type;
    }

    public AiNode getPrevious() {
        return previous;
    }

    public boolean isLast(){
        return previous == null;
    }

}
