package creatures.animals;

import debuger.SettlerDebuger;
import map.ground.Title;
import map.structures.Structure2D;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public abstract class Animal2D implements Animal, SettlerDebuger {

    protected double x, y;
    protected int width, height;
    List<Title> way;
    protected int timeToNextMove = 500, timeToHungry = 1000;
    protected long lastMove = 0, lastHungry = 0;
    private short hungry;
    protected boolean foodSearching = false, eating = false;

    public Animal2D(){
        hungry = (short) (new Random().nextInt(10) + 30);
    }

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
        if(lastHungry + timeToHungry < System.currentTimeMillis() && hungry > 0){
            hungry--;
            lastHungry = System.currentTimeMillis();
        }
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
        if(Objects.isNull(structure) && foodSearching){
            if(way.isEmpty()){
                foodSearching = false;
                return;
            }
        }
        if(foodSearching && way.isEmpty() && structure.toString().equals(Structure2D.getStructure(Structure2D.PLANT).toString())){
            eating = true;
            foodSearching = false;
            hungry += 50;
        }
    }

    @Override
    public String getCornerText() {
        StringBuilder debugText = new StringBuilder();
        debugText.append("position: x: " + x + " y: " + y + System.lineSeparator());
        debugText.append("Size: " + width + " x " + height + System.lineSeparator());
        debugText.append("Type: " + toString() + System.lineSeparator());
        debugText.append("hungry: " + hungry + System.lineSeparator());
        debugText.append("food search: " + (isFoodSearch()? "yes" : "no") + System.lineSeparator());
        if(way != null)
            debugText.append("have way: " +(!way.isEmpty()? "yes" : "no") + System.lineSeparator());
        else
            debugText.append("have way: " + "no" + System.lineSeparator());
        return debugText.toString();
    }

    @Override
    public void update() {
            move();
            makeHungry();
    }
}
