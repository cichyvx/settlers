package gamepanles;

import gamepanles.panelListeners.ExitListener;

import javax.swing.*;
import java.awt.*;

public class OptionsPane extends JPanel implements GamePanel2D {

    private final int WIDTH, HEIGHT;
    private int status;
    private ExitListener exitListener;

    public OptionsPane(int width, int height){
        this.WIDTH = width;
        this.HEIGHT = height;
        this.status = 0;
        exitListener = new ExitListener();
        addKeyListener(exitListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
        g2d.fillRect(0,0,WIDTH,HEIGHT);
    }

    @Override
    public void update() {
        if(exitListener.isEscaped()) status = -1;
    }

    @Override
    public void draw() {
        repaint();
    }

    @Override
    public int getStatus() {
        return status;
    }
}
