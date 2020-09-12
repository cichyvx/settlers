package gamepanles.panelListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseGameListener implements MouseMotionListener, java.awt.event.MouseListener {

    private int x,y;
    private boolean clicked;

    public boolean isClicked() {
        if(clicked){
            clicked = false;
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public MouseGameListener(){
        x = 0;
        y = 0;
        clicked = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getXOnScreen();
        y = e.getYOnScreen();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getXOnScreen();
        y = e.getYOnScreen();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getXOnScreen();
        y = e.getYOnScreen();
        clicked = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getXOnScreen();
        y = e.getYOnScreen();
        clicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x = e.getXOnScreen();
        y = e.getYOnScreen();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        x = e.getXOnScreen();
        y = e.getYOnScreen();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        x = e.getXOnScreen();
        y = e.getYOnScreen();
    }
}
