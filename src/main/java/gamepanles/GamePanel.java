package gamepanles;

import core.App;
import creatures.animals.Animal;
import debuger.DebugingObject;
import gamepanles.panelListeners.CameraListener;
import gamepanles.panelListeners.ExitListener;
import gamepanles.panelListeners.KeyBindListener;
import gamepanles.panelListeners.MouseGameListener;
import map.Map;
import map.ground.Title;
import map.structures.Structure2D;

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
    private KeyBindListener keyBindListener;
    private int selected = 0;
    private boolean debug = false;
    private DebugingObject debugingObject;

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
        keyBindListener = new KeyBindListener();
        mouseGameListener = new MouseGameListener();
        addKeyListener(exitListener);
        addKeyListener(cameraListener);
        addKeyListener(keyBindListener);
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

        /* ANIMAL DRAWING */
        for(Animal animal: map.animals_AI.getAnimals()){
            g2d.drawImage(map.graphicsHandler.getImage(animal.toString()), (int) animal.getX(), (int) animal.getY(), sWeight, sHeight, null);
        }

        /* DEBUGER DRAWING */
        if(debug){
            g2d.setColor(Color.WHITE);
            int h = g2d.getFontMetrics().getHeight() - (int) transY;
            for(String lines : debugingObject.getCornerText().split(System.lineSeparator())){
                g2d.drawString(lines, (int) -transX, h);
                h += g2d.getFontMetrics().getHeight();
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

        debug = keyBindListener.isDebugOn();

        setTranslation(cameraListener.getTranslateX(), cameraListener.getTranslateY());

        point.x = (int) (mouseGameListener.getX() - transX);
        point.y = (int) (mouseGameListener.getY() - transY);

        if(exitListener.isEscaped()) status = -1;

        if(mouseGameListener.isClicked()){
            boolean finded = false;
            Point point = new Point(mouseGameListener.getX(), mouseGameListener.getY());
            for (Animal animal : map.animals_AI.getAnimals()){
                if(new Rectangle((int) animal.getX(), (int) animal.getY(), 25, 25).contains(point)){
                    debugingObject = new DebugingObject(animal);
                    finded = true;
                    break;
                }
            }

            if(!finded){
                for(Title[] titles : map.titles){
                    for(Title title : titles){
                        if(title.getRectange(25, 25).contains(point)){
                            debugingObject = new DebugingObject(title);
                            finded = true;
                            break;
                        }
                    }
                }
            }
        }

        map.animals_AI.update();
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
