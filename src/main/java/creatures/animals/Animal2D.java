package creatures.animals;

import map.ground.Title;

import java.util.List;

public abstract class Animal2D implements Animal{

    protected double x, y;
    protected int width, height;
    List<Title> way;
    protected int time = 500;
    protected long lastMove = 0;

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
    public void setWay(List<Title> titles) {
        this.way = titles;
    }

    protected void move(){
        if(way == null) return;
        if(way.isEmpty()) return;
        if(lastMove + time < System.currentTimeMillis()){
            x = way.get(0).getX();
            y = way.get(0).getY();
            way.remove(0);
            lastMove = System.currentTimeMillis();
            System.out.println("move");
        }
    }

    @Override
    public void update() {
            move();
    }
}
