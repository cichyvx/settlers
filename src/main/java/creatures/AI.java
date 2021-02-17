package creatures;

import creatures.animals.Animal;
import map.Map;
import map.ground.Title;
import map.structures.Structure2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AI {
    private Map map;
    private List<Animal> animals;

    /**
     *
     * @param map the map on which the AI is supposed to work
     */

    public AI(Map map){
        this.map = map;
        animals = new ArrayList<>();
    }

    /**
     * @return a list of all animals on the map
     */

    public List<Animal> getAnimals(){
        return animals;
    }


    //todo remove bug with Array index out of bounds

    /**
     * method used to find the nearest desired fields for a specific animal
     * @param searching the list of structures we are looking for
     * @param animal the animal that is looking for
     * @param <T> any structure
     * @param <Y> any animal
     * @return list of fields leading to the nearest structure searched
     * @throws ArrayIndexOutOfBoundsException when it cannot find any of the searched structures
     */

    public <T extends Structure2D, Y extends Animal> List<Title> searchWayTo(ArrayList<T> searching, Y animal) throws ArrayIndexOutOfBoundsException{
        if(searching == null)
            return null;

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
                boolean up = node.i - 1 >= 0, right = node.j < map.titles[node.i].length,
                        bottom = node.i + 1 < map.titles.length, left = node.j - 1 >= 0;


                /* up - left */
                if(up && left){
                    if(map.titles[node.i - 1][node.j - 1].isWalking() && !isVisited(map.titles[node.i - 1][node.j - 1], visited)){
                        AiNode newnode = new AiNode(node.i - 1, node.j - 1, node, map.titles[node.i - 1][node.j - 1].getStructure());
                        nextNodes.add(newnode);
                    }
                    visited.add(map.titles[node.i - 1][node.j - 1]);
                }

                /* up */
                if(up){
                    if(map.titles[node.i - 1][node.j].isWalking() && !isVisited(map.titles[node.i - 1][node.j], visited)){
                        AiNode newnode = new AiNode(node.i - 1, node.j, node, map.titles[node.i - 1][node.j].getStructure());
                        nextNodes.add(newnode);
                    }
                    visited.add(map.titles[node.i - 1][node.j]);
                }

                /* up - right */
                if(up && right){
                    if(map.titles[node.i - 1][node.j + 1].isWalking() && !isVisited(map.titles[node.i - 1][node.j + 1], visited)){
                        AiNode newnode = new AiNode(node.i - 1, node.j + 1, node, map.titles[node.i - 1][node.j + 1].getStructure());
                        nextNodes.add(newnode);
                    }
                    visited.add(map.titles[node.i - 1][node.j + 1]);
                }

                /* right */
                if(right){
                    if(map.titles[node.i][node.j + 1].isWalking() && !isVisited(map.titles[node.i][node.j + 1], visited)){
                        AiNode newnode = new AiNode(node.i, node.j + 1, node, map.titles[node.i][node.j + 1].getStructure());
                        nextNodes.add(newnode);
                    }
                    visited.add(map.titles[node.i][node.j + 1]);
                }

                /* bottom - right */
                if(bottom && right){
                    if(map.titles[node.i + 1][node.j + 1].isWalking() && !isVisited(map.titles[node.i + 1][node.j + 1], visited)){
                        AiNode newnode = new AiNode(node.i + 1, node.j + 1, node, map.titles[node.i + 1][node.j + 1].getStructure());
                        nextNodes.add(newnode);
                    }
                    visited.add(map.titles[node.i + 1][node.j + 1]);
                }

                /* bottom */
                if(bottom){
                    if(map.titles[node.i + 1][node.j].isWalking() && !isVisited(map.titles[node.i + 1][node.j], visited)){
                        AiNode newnode = new AiNode(node.i + 1, node.j, node, map.titles[node.i + 1][node.j].getStructure());
                        nextNodes.add(newnode);
                    }
                    visited.add(map.titles[node.i + 1][node.j]);
                }

                /* bottom - left */
                if(bottom && left){
                    if(map.titles[node.i + 1][node.j - 1].isWalking() && !isVisited(map.titles[node.i + 1][node.j - 1], visited)){
                        AiNode newnode = new AiNode(node.i + 1, node.j - 1, node, map.titles[node.i + 1][node.j - 1].getStructure());
                        nextNodes.add(newnode);
                    }
                    visited.add(map.titles[node.i + 1][node.j - 1]);
                }

                /* left */
                if(left){
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
                for(Structure2D struct: searching){
                    if(node.type.toString().equals(struct.toString())){
                        finishedNode = node;
                        break;
                    }
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

    /**
     * the method is used to check whether a given field is included in the list being searched
     * @param title the field you are looking for
     * @param visited list of visited fields
     * @return returns true if the field is included in the list and false if not
     */
    private boolean isVisited(Title title, List<Title> visited) {
        for(Title t: visited){
            if(t.equals(title))
                return true;
        }
        return false;
    }

    /**
     * replace current animal list with list from parameter
     * @param animals new animal list
     */
    public void setAnimals(List<Animal> animals){
        this.animals = animals;
    }

    /**
     * adding a new animal to the list
     * @param animal a new animal to be added to the list
     */
    public void add(Animal animal) {
        animals.add(animal);
    }

    /**
     * the method is used to update the current state of all animals on the list.
     * the method performs the update method on each of the animals,
     * then, if there is such a need,
     * finds the necessary path for them or checks whether they have already reached their destination
     */
    public void update() {
        for(Animal animal: animals){
            animal.update();

            if (animal.needFood() && !animal.isFoodSearch()){
                try{
                    animal.setWay(searchWayTo(animal.getFoodList(), animal));
                    animal.foodSearch(true);
                }catch (ArrayIndexOutOfBoundsException ignore){}
            }

            else if(animal.needFood()){
                for (int i = 0; i < map.titles.length; i++){
                    if((int)animal.getX() < i * animal.getHeight())
                        continue;
                    for(int j = 0; j < map.titles[i].length; j++){
                        if(map.titles[i][j].getRectange(25, 25).contains(animal.getX(), animal.getY())){
                            animal.eat(map.titles[i][j].getStructure());
                            map.titles[i][j].deleteStructure();
                            break;
                        }
                    }
                }
            }


        }
    }

    /**
     * methods generates a random number of animals on the map
     */
    public void generateAnimals() {
        int animalCount = new Random().nextInt(20) + 20;
        Point point = new Point();
        Random r = new Random();
        for (int i = 0; i < animalCount; i++){
            Title title;
            do{
                title = map.titles[r.nextInt(map.titles.length - 1) + 1][r.nextInt(map.titles[0].length - 1) + 1];
            }while (!title.isWalking());
            point.x = title.getX();
            point.y = title.getY();
            Animal animal = Animal.getRandomAnimal();
            animal.setX(point.x);
            animal.setY(point.y);
            animals.add(animal);
        }
    }
}
