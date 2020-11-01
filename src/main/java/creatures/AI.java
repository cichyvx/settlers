package creatures;

import creatures.animals.Animal;
import map.Map;
import map.ground.Title;

import java.util.ArrayList;
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

    public void updateTitle(Title title, int i, int j){
        map.titles[i][j] = title;
    }

    public List<Integer[]> getRandomWay(int r, int i, int j){
        //if (map.titles.length - r >= 0 && r + i < map.titles.length)
        return null;
    }

    public void setAnimals(List<Animal> animals){
        this.animals = animals;
    }

    public void add(Animal animal) {
        animals.add(animal);
    }
}
