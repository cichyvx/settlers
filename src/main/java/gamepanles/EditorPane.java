package gamepanles;

import gamepanles.panelListeners.ExitListener;
import map.Map;

import javax.swing.*;
import java.awt.*;

public class EditorPane extends JPanel implements GamePanel2D {

    private final int WIDTH, HEIGHT;
    private int status,optionalStatus;
    private final ExitListener exitListener;

    private final int sWeight, sHeight;

    private Map map;

    public EditorPane(int width, int height){
        optionalStatus = 0;
        this.WIDTH = width;
        this.HEIGHT = height;
        status = 0;
        exitListener = new ExitListener();
        addKeyListener(exitListener);

        sWeight = 25;
        sHeight = 25;
        map = new Map(50, 50, sWeight, sHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for(int i = 0; i < map.getHEIGHT(); i++){
            for(int j = 0; j < map.getWIDTH(); j++){
                int x = map.titles[i][j].getX();
                int y = map.titles[i][j].getY();
                g2d.drawImage(map.graphicsHandler.getImage(map.titles[i][j].toString()),x,y,sWeight,sHeight,null);
            }
        }



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

    @Override
    public int getOptionalStatus() {
        return optionalStatus;
    }
}
