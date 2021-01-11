package creatures.animals;

public class Cow extends Animal2D{

    public Cow(double x, double y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    @Override
    public String toString() {
        return "COW";
    }
}
