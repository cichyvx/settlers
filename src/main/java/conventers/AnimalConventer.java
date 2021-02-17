package conventers;

import creatures.animals.Animal;

/**
 * the class is used to convert animals to one typo in order to save them,
 * among others, to a JSON file, or to convert them from the current class
 * to the state before conversion
 */

public class AnimalConventer {

    private double x, y;
    private int width, height;
    private String name;

    /**
     * turns any animal into a safe write-ready type
     * @param animal animal to be converted
     */
    public AnimalConventer(Animal animal){
        this.x = animal.getX();
        this.y = animal.getY();
        this.width = animal.getWidth();
        this.height = animal.getHeight();
        this.name = animal.toString();
    }


    /**
     *
     * @param animalConventer safe type to be converted
     * @return original type before converting
     */
    public static Animal AgetAnimal(AnimalConventer animalConventer){
        Animal animal = Animal.getAnimal(animalConventer.name);
        animal.setX(animalConventer.x);
        animal.setY(animalConventer.y);
        animal.setWeight(animalConventer.width);
        animal.setHeight(animalConventer.height);
        return animal;
    }

}
