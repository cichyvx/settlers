package gamepanles.panelListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ExitListener implements KeyListener {

    private boolean escape = false;

    public boolean isEscaped() {
        if(escape){
            escape = false;
            return true;
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            escape = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            escape = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
