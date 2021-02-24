package creatures.animals;

import debuger.SettlerDebuger;
import map.ground.Title;
import map.structures.BerriesStructure;
import map.structures.PlantStructure;
import map.structures.Structure2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public abstract class Animal2D implements Animal, SettlerDebuger {

    protected double x, y;
    protected int width, height;

    List<Title> way;

    protected int timeToNextMove = 500, timeToHungry = 5000, timeToNextEat = 3000;
    protected long lastMove = 0, lastHungry = 0, lastEat = 0;
    private short hungry;
    protected boolean foodSearching = false, eating = false;

    public Animal2D(){
        hungry = (short) (new Random().nextInt(70) + 30);
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

    /**
     * moves the animal to the first position on the list and then,
     * removes the first item and replaces it with the next item
     */
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

    /**
     * increases the animal's hunger level (default by 5)
     */
    private void makeHungry(){
        if(lastHungry + timeToHungry < System.currentTimeMillis() && hungry > 0){
            hungry -= 5 ;
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
        if(System.currentTimeMillis() < lastEat + timeToNextEat){
            return;
        }
        if(Objects.isNull(structure) && foodSearching){
            if(way == null){
                foodSearching = false;
                //todo fix that later
                System.err.println("CANT FIND WAY OR SOMTHING ELSE FIX THIS LATER");
                lastEat = System.currentTimeMillis();
                return;
            }
            if(way.isEmpty()){
                foodSearching = false;
                lastEat = System.currentTimeMillis();
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
        debugText.append("position: x: ").append(x)
                .append(" y: ")
                .append(y).append(System.lineSeparator());

        debugText.append("Size: ")
                .append(width).append(" x ")
                .append(height)
                .append(System.lineSeparator());

        debugText.append("Type: ")
                .append(toString())
                .append(System.lineSeparator());

        debugText.append("hungry: ")
                .append(hungry)
                .append(System.lineSeparator());

        debugText.append("food search: ")
                .append(isFoodSearch() ? "yes" : "no")
                .append(System.lineSeparator());

        if(way != null)
            debugText.append("have way: ")
                    .append(!way.isEmpty() ? "yes" : "no")
                    .append(System.lineSeparator());

        else
            debugText.append("have way: " + "no")
                    .append(System.lineSeparator());

        return debugText.toString();
    }

    @Override
    public ArrayList<Structure2D> getFoodList() {
        ArrayList<Structure2D> list = new ArrayList<>();
        if(this.getClass().equals(Cow.class)){
            list.add(new PlantStructure());
            list.add(new BerriesStructure());
        }
        return list;
    }

    @Override
    public void update() {
            move();
            makeHungry();
    }
}
