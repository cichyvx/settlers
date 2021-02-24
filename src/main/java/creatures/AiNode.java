package creatures;

import map.ground.Title;
import map.structures.Structure2D;

/**
 * the class works on the principle of an ordinary one-way list,
 * where we only have access to its predecessor from the object
 * @param <T> the object to be stored in this list item
 */
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
