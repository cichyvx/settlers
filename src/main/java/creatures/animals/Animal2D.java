package creatures.animals;

import java.util.List;

public abstract class Animal2D implements Animal{

    protected double x, y;
    protected int width, height;
    List<Integer[]> way;

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setWeight(int weight) {
        this.width = weight;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }


    @Override
    public void update() {

    }
}
