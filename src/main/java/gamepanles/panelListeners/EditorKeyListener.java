package gamepanles.panelListeners;

import map.ground.Title;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EditorKeyListener implements KeyListener {

    int selected;

    public EditorKeyListener(){
        selected = 1;
    }

    public int getSelected() {
        return selected;
    }

    private void next(){
        if(selected + 1 > Title.getTitleCount()){
            selected = 1;
            return;
        }
        selected++;
    }

    private void previous(){
        if(selected - 1 < 1){
            selected = Title.getTitleCount();
            return;
        }
        selected--;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_PERIOD)
            next();
        if(e.getKeyCode() == KeyEvent.VK_COMMA)
            previous();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_PERIOD)
            next();
        if(e.getKeyCode() == KeyEvent.VK_COMMA)
            previous();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
