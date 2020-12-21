package creatures;

import creatures.animals.Animal;
import map.Map;
import map.ground.Title;
import map.structures.Structure2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AI {
    private Map map;
    private List<Animal> animals;

    public AI(Map map){
        this.map = map;
        animals = new ArrayList<>();
    }

    public List<Animal> getAnimals(){
        return animals;
    }

    public <T extends Structure2D, Y extends Animal> List<Title> SearchWayTo(T searching, Y animal){
        AiNode finishedNode = null;
        Point point = new Point((int)animal.getX(), (int)animal.getY());
        AiNode starting = null;
        List<Title> visited = new ArrayList<>();

        assert point != null;

        for (int i = 0; i < map.titles.length; i++){
            for(int j = 0; j < map.titles[i].length; j++){
                if(map.titles[i][j].getRectange(25, 25).contains(animal.getX(), animal.getY())){
                    starting = new AiNode(i, j, map.titles[i][j].getStructure());
                    visited.add(map.titles[i][j]);
                    point = null;
                    break;
                }
            }
        }

        assert starting != null;

        List<AiNode> nodes = new ArrayList<>();
        nodes.add(starting);

        while (finishedNode == null){
            List<AiNode> nextNodes = new ArrayList<>();
            for(AiNode node: nodes){

                /* up */
                if(node.i - 1 >= 0){
                    if(map.titles[node.i - 1][node.j].isWalking() && !isVisited(map.titles[node.i - 1][node.j], visited)){
                        AiNode newnode = new AiNode(node.i - 1, node.j, node, map.titles[node.i - 1][node.j].getStructure());
                        nextNodes.add(newnode);
                    }
                    visited.add(map.titles[node.i - 1][node.j]);
                }

                /* right */
                if(node.j + 1 < map.titles[node.i].length){
                    if(map.titles[node.i][node.j + 1].isWalking() && !isVisited(map.titles[node.i][node.j + 1], visited)){
                        AiNode newnode = new AiNode(node.i, node.j + 1, node, map.titles[node.i][node.j + 1].getStructure());
                        nextNodes.add(newnode);
                    }
                    visited.add(map.titles[node.i][node.j + 1]);
                }

                /* bottom */
                if(node.i + 1 < map.titles.length){
                    if(map.titles[node.i + 1][node.j].isWalking() && !isVisited(map.titles[node.i + 1][node.j], visited)){
                        AiNode newnode = new AiNode(node.i + 1, node.j, node, map.titles[node.i + 1][node.j].getStructure());
                        nextNodes.add(newnode);
                    }
                    visited.add(map.titles[node.i + 1][node.j]);
                }

                /* left */
                if(node.j - 1 >= 0){
                    if(map.titles[node.i][node.j - 1].isWalking() && !isVisited(map.titles[node.i][node.j - 1], visited)){
                        AiNode newnode = new AiNode(node.i, node.j - 1, node, map.titles[node.i][node.j - 1].getStructure());
                        nextNodes.add(newnode);
                    }
                    visited.add(map.titles[node.i][node.j - 1]);
                }

            }

            if(nextNodes.isEmpty())
                return null;

            for(AiNode node: nextNodes){
                if(node.type == null)
                    continue;
                if(node.type.toString().equals(searching.toString())){
                    finishedNode = node;
                    break;
                }
            }

            nodes = nextNodes;

        }

        List<Title> finished = new ArrayList<>();
        while (finishedNode != null){
            finished.add(map.titles[finishedNode.i][finishedNode.j]);
            finishedNode = finishedNode.previous;
        }
        Collections.reverse(finished);
        return finished;
    }

    private boolean isVisited(Title title, List<Title> visited) {
        for(Title t: visited){
            if(t.equals(title))
                return true;
        }
        return false;
    }

    public void setAnimals(List<Animal> animals){
        this.animals = animals;
    }

    public void add(Animal animal) {
        animals.add(animal);
    }

    public void update() {
        for(Animal animal: animals)
            animal.update();
    }
}
