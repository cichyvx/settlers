package creatures.animals;

import map.ground.Title;

import java.util.List;

public interface Animal {

    static Animal getAnimal(int selected) {
        switch (selected){
            case 1:
                return new Cow(0,0,0,0);
            default:
                return new Cow(0,0,0,0);
        }
    }

    static Animal getAnimal(String name) {
        switch (name){
            case "COW":
                return new Cow(0,0,0,0);
            default:
                return new Cow(0,0,0,0);
        }
    }

    double getX();
    double getY();
    int getWidth();
    int getHeight();

    void setX(double x);
    void setY(double y);
    void setWeight(int weight);
    void setHeight(int height);
    void setWay(List<Title> titles);
    void update();

    static int getAnimalsCount(){
        return 1;
    }

}
