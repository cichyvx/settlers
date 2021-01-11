package gamepanles.panelListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBindListener implements KeyListener {

    private boolean debugOn = false;

    public boolean isDebugOn() {
        return debugOn;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_PERIOD)
            debugOn = !debugOn;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
