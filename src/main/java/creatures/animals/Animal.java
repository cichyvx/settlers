package creatures.animals;

import debuger.SettlerDebuger;
import map.ground.Title;
import map.structures.Structure2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface Animal extends SettlerDebuger {

    /**
     * returns the Animal associated with the search id
     * @param selected ID of the animal you are looking for
     * @return animal assigned to the search id (if the id does not match the rare animal, Cow will be returned)
     */
    static Animal getAnimal(int selected) {
        switch (selected){
            case 1:
                return new Cow(0,0,0,0);
            default:
                return new Cow(0,0,0,0);
        }
    }

    /**
     * returns the Animal associated with the search name
     * @param name name of the animal (in capital letters)
     * @return animal assigned to the search name (if the id does not match the rare animal, Cow will be returned)
     */
    static Animal getAnimal(String name) {
        switch (name){
            case "COW":
                return new Cow(0,0,0,0);
            default:
                return new Cow(0,0,0,0);
        }
    }

    /**
     * returns any random animal
     * @returns any random animal
     */
    static Animal getRandomAnimal() {
        return getAnimal(Math.abs(new Random().nextInt(getAnimalsCount() ) ));
    }

    /**
     * get Vertical position
     * @return return Vertical position
     */
    double getX();

    /**
     * get horizontal position
     * @return horizontal position
     */
    double getY();

    /**
     * get Width of animal
     * @return Width
     */
    int getWidth();

    /**
     * get Height of animal
     * @return Height of animal
     */
    int getHeight();

    /**
     * checks if the animal needs food
     * @return true if animal wants food
     */
    boolean needFood();

    /**
     * ser Vertical position of animal
     * @param x new position of animal
     */
    void setX(double x);

    /**
     * set Horizontal position of animal
     * @param y new position of animal
     */
    void setY(double y);

    /**
     * set Width of animal
     * @param weight new Width of animal
     */
    void setWeight(int weight);

    /**
     * set Height of animal
     * @param height new Height of animal
     */
    void setHeight(int height);

    /**
     * sets a new path on which the animal is to move
     * @param titles list of fields on which the animal is to go
     */
    void setWay(List<Title> titles);

    /**
     * update the current animal status
     */
    void update();

    /**
     * a method by which the structure is passed on to animals and eaten
     * @param structure structure to be eaten
     */
    void eat(Structure2D structure);

    /**
     * returns the number of animal types
     * @return number of animal types
     */
    static int getAnimalsCount(){
        return 1;
    }

    /**
     * checks if the animal is looking for food
     * @return true if animal is currently looking for food
     */
    boolean isFoodSearch();

    /**
     * set true if the animal is currently looking for food, or false if it is not
     * @param b state of lloking for food by animal
     */
    void foodSearch(boolean b);

    /**
     * the method is used to provide a list of available types of food for a specific animal
     * @return list of available types of food for animal
     */
    ArrayList<Structure2D> getFoodList();
}
