package creatures.animals;

public class Rabbit extends Animal2D{

    public Rabbit(double x, double y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    @Override
    public String toString() {
        return "RABBIT";
    }
}
