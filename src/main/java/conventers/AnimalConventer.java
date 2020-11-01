package conventers;

import creatures.animals.Animal;

public class AnimalConventer {

    private double x, y;
    private int width, height;
    private String name;

    public AnimalConventer(Animal animal){
        this.x = animal.getX();
        this.y = animal.getY();
        this.width = animal.getWidth();
        this.height = animal.getHeight();
        this.name = animal.toString();
    }


    public static Animal AgetAnimal(AnimalConventer animalConventer){
        Animal animal = Animal.getAnimal(animalConventer.name);
        animal.setX(animalConventer.x);
        animal.setY(animalConventer.y);
        animal.setWeight(animalConventer.width);
        animal.setHeight(animalConventer.height);
        return animal;
    }

}
