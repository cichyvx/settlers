package gamepanles.panelListeners;

import creatures.animals.Animal;
import map.ground.Title;
import map.structures.Structure2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EditorKeyListener implements KeyListener {

    private int selected;
    private int objectType;
    private final int MAXOBJECT;

    public static final int GROUND = 1, STRUCTURE = 2, ANIMAL = 3;

    public EditorKeyListener(){
        selected = 1;
        objectType = 1;
        MAXOBJECT = 3;
    }

    public int getObjectType(){
        return objectType;
    }

    public int getSelected() {
        return selected;
    }

    private void next(){
        switch (objectType){
            case 1:
                if(selected + 1 > Title.getTitleCount()){
                    selected = 1;
                    break;
                }
                selected++;
                break;

            case 2:
                if (selected + 1 > Structure2D.getStructuresCount()){
                    selected = 1;
                    break;
                }
                selected++;
                break;

            case 3:
                if(selected + 1 > Animal.getAnimalsCount()){
                    selected = 1;
                }
                selected++;
                break;
        }
    }

    private void previous(){
        switch (objectType){
            case 1:
                if(selected - 1 < 1){
                    selected = Title.getTitleCount();
                    break;
                }
                selected--;
                break;

            case 2:
                if(selected - 1 < 1){
                    selected = Structure2D.getStructuresCount();
                    break;
                }
                selected--;
                break;

            case 3:
                if(selected - 1 < 1){
                    selected = Animal.getAnimalsCount();
                    break;
                }
                selected--;
                break;
        }

    }

    private void nextType(){
        selected = 1;
        if(objectType + 1 > MAXOBJECT){
            objectType = 1;
            System.out.println(objectType);
            return;
        }
        System.out.println(objectType);
        objectType++;
    }

    private void previousType(){
        selected = 1;
        if(objectType - 1 < 1){
            objectType = MAXOBJECT;
            System.out.println(objectType);
            return;
        }
        System.out.println(objectType);
        objectType--;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            next();
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            previous();
        if(e.getKeyCode() == KeyEvent.VK_UP)
            nextType();
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            previousType();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            next();
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            previous();
        if(e.getKeyCode() == KeyEvent.VK_UP)
            nextType();
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            previousType();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
