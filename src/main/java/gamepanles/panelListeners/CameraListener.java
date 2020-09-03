package gamepanles.panelListeners;

import core.App;

import java.awt.event.*;

public class CameraListener implements KeyListener, MouseListener, MouseWheelListener, MouseMotionListener {

    private int translateX, translateY;
    private double scale;
    private int mouseX, mouseY;

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public CameraListener(){
        translateX = 0;
        translateY = 0;
        scale = 0;
    }

    public double getScale() {
        double x = scale;
        scale = 0;
        return x;
    }

    public int getTranslateX(){
        return translateX;
    }

    public int getTranslateY() {
        return translateY;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
            translateY = 1;
        }

        else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
            translateY = -1;
        }


        if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
            translateX = -1;
        }

        else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
            translateX = 1;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        boolean horizontal = (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN);
        boolean vertical = (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT);
        if(horizontal) translateY = 0;
        if(vertical) translateX = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scale = e.getPreciseWheelRotation();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getXOnScreen();
        mouseY = e.getYOnScreen();
    }
}
