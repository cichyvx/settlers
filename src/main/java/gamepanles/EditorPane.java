package gamepanles;

import gamepanles.panelListeners.CameraListener;
import gamepanles.panelListeners.ExitListener;
import map.Map;

import javax.swing.*;
import java.awt.*;

public class EditorPane extends JPanel implements GamePanel2D {

    private final int WIDTH, HEIGHT;
    private double transX, transY,scale;
    private int status,optionalStatus;
    private final ExitListener exitListener;
    private CameraListener cameraListener;


    private final int sWeight, sHeight;

    private Map map;

    public EditorPane(int width, int height){
        optionalStatus = 0;
        this.WIDTH = width;
        this.HEIGHT = height;
        status = 0;
        exitListener = new ExitListener();
        cameraListener = new CameraListener();

        addKeyListener(exitListener);
        addKeyListener(cameraListener);

        addMouseListener(cameraListener);

        addMouseMotionListener(cameraListener);

        addMouseWheelListener(cameraListener);

        sWeight = 25;
        sHeight = 25;
        transX = 0;
        transY = 0;
        scale = 1;

        map = new Map(50, 50, sWeight, sHeight,true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(transX, transY);
        g2d.scale(scale, scale);

        g2d.setColor(Color.black);
        g2d.fillRect(-WIDTH,-HEIGHT,WIDTH * WIDTH,HEIGHT * HEIGHT);
        g2d.setBackground(Color.black);


        for(int i = 0; i < map.getHEIGHT(); i++){
            for(int j = 0; j < map.getWIDTH(); j++){
                int x = map.titles[i][j].getX();
                int y = map.titles[i][j].getY();
                g2d.drawImage(map.graphicsHandler.getImage(map.titles[i][j].toString()),x,y,sWeight,sHeight,null);
            }
        }



    }

    private void setTranslation(int rightWise, int upWise){
        if(upWise > 0) transY += 2;
        if( upWise < 0) transY -= 2;

        if(rightWise > 0) transX += 2;
        if(rightWise < 0) transX -= 2;
    }

    private void setScale(double scale) {
        if(scale == 0) return;
        final int precision = 20;
        this.scale += scale/precision;
    }

    @Override
    public void update() {
        if(exitListener.isEscaped()) status = -1;
        setTranslation(cameraListener.getTranslateX(), cameraListener.getTranslateY());
        setScale(cameraListener.getScale());
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
