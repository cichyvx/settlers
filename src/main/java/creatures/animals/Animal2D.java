package creatures.animals;

import map.ground.Title;
import map.structures.Structure2D;

import java.util.List;

public abstract class Animal2D implements Animal{

    protected double x, y;
    protected int width, height;
    List<Title> way;
    protected int timeToNextMove = 500, timeToHungry = 1000;
    protected long lastMove = 0, lastHungry = 0;
    private short hungry = 0;
    protected boolean foodSearching = false, eating = false;

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
        if(lastMove + timeToNextMove < System.currentTimeMillis()){
            x = way.get(0).getX();
            y = way.get(0).getY();
            way.remove(0);
            lastMove = System.currentTimeMillis();
        }
    }

    private void makeHungry(){
        if(lastHungry + timeToHungry < System.currentTimeMillis() && hungry > 0)
            hungry--;
    }

    @Override
    public boolean needFood() {
        return hungry <= 30;
    }

    @Override
    public boolean isFoodSearch() {
        return foodSearching;
    }

    @Override
    public void foodSearch(boolean b) {
        foodSearching = b;
    }

    @Override
    public void eat(Structure2D structure) {
        if(foodSearching && (way.isEmpty() || way == null)){
            eating = true;
            foodSearching = false;
            hungry += 50;
        }
    }

    @Override
    public void update() {
            move();
            makeHungry();
    }
}
