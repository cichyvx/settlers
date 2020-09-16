package gamepanles;

import gamepanles.panelListeners.CameraListener;
import gamepanles.panelListeners.ExitListener;
import gamepanles.panelListeners.MouseGameListener;
import map.Map;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements GamePanel2D{

    private final int WIDTH, HEIGHT;
    private int status,optionalStatus;
    private ExitListener exitListener;
    private final int sWeight, sHeight;
    private final Map map;
    private double transX, transY;
    private Point point;
    private CameraListener cameraListener;
    private MouseGameListener mouseGameListener;

    public GamePanel(int width, int height, int mapWidth, int mapHeight, Map map){
        optionalStatus = 0;
        this.setSize(width, height);
        this.WIDTH = width;
        this.HEIGHT = height;
        this.sWeight = mapWidth;
        this.sHeight = mapHeight;
        status = 0;
        exitListener = new ExitListener();
        cameraListener = new CameraListener();
        mouseGameListener = new MouseGameListener();
        addKeyListener(exitListener);
        addKeyListener(cameraListener);
        addMouseListener(mouseGameListener);
        this.map = map;
        transX = 0;
        transY = 0;
        point = new Point();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(transX, transY);
        //g2d.scale(scale, scale); // scale will be added soon

        g2d.setColor(Color.black);
        g2d.fillRect(-WIDTH, -HEIGHT, WIDTH * WIDTH, HEIGHT * HEIGHT);
        g2d.setBackground(Color.black);


        /* MAP DRAWING */
        for (int i = 0; i < map.getHEIGHT(); i++) {
            for (int j = 0; j < map.getWIDTH(); j++) {
                int x = map.titles[i][j].getX();
                int y = map.titles[i][j].getY();
                g2d.drawImage(map.graphicsHandler.getImage(map.titles[i][j].toString()), x, y, sWeight, sHeight, null);
                if (map.titles[i][j].haveObject()) {
                    g2d.drawImage(map.graphicsHandler.getImage(map.titles[i][j].getStructure().toString()), x, y, sWeight, sHeight, null);

                }
            }
        }
    }

    private void setTranslation(int rightWise, int upWise) {
        if (upWise > 0) transY += 2;
        if (upWise < 0) transY -= 2;

        if (rightWise > 0) transX += 2;
        if (rightWise < 0) transX -= 2;
    }

    @Override
    public void update() {

        setTranslation(cameraListener.getTranslateX(), cameraListener.getTranslateY());

        point.x = (int) (mouseGameListener.getX() - transX);
        point.y = (int) (mouseGameListener.getY() - transY);

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
