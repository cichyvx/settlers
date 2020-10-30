package gamepanles;

import gamepanles.panelListeners.*;
import map.Map;
import map.ground.Title;
import map.structures.Structure2D;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditorPane extends JPanel implements GamePanel2D {

    private final int WIDTH, HEIGHT;
    private double transX, transY, scale;
    private int status, optionalStatus;
    private final ExitListener exitListener;
    private final CameraListener cameraListener;
    private ExitingEditorPane exitingEditorPane;
    private final EditorKeyListener editorKeyListener;
    private boolean exiting;
    private final MouseGameListener mouseGameListener;
    private final Point point;

    private int sWeight;
    private int sHeight;


    private final Map map;

    public EditorPane(int width, int height, int mapWidth, int mapHeight, int nRiver, int nRock) {
        point = new Point(0,0);
        exiting = false;
        optionalStatus = 0;
        this.WIDTH = width;
        this.HEIGHT = height;
        status = 0;

        exitListener = new ExitListener();
        cameraListener = new CameraListener();
        mouseGameListener = new MouseGameListener();
        editorKeyListener = new EditorKeyListener();

        initializeKeyListeners();
        initializeSettings();

        map = new Map(mapWidth, mapHeight, sWeight, sHeight, true, nRiver, nRock);
    }

    private void initializeKeyListeners(){
        addKeyListener(exitListener);
        addKeyListener(cameraListener);
        addKeyListener(editorKeyListener);
        addMouseMotionListener(mouseGameListener);
        addMouseListener(mouseGameListener);
        addMouseListener(cameraListener);
        addMouseMotionListener(cameraListener);
        addMouseWheelListener(cameraListener);
    }

    private void initializeSettings(){
        sWeight = 25;
        sHeight = 25;
        transX = 0;
        transY = 0;
        scale = 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(transX, transY);

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

        /* RIGHT BOTTOM ICON DRAWING */
        if(editorKeyListener.getObjectType() == EditorKeyListener.GROUND){
            Title title = Title.getTitle(editorKeyListener.getSelected(), WIDTH - sWeight, HEIGHT - sHeight);
            assert title != null;
            g2d.drawImage(map.graphicsHandler.getImage(title.toString()), WIDTH - sWeight - (int)transX, HEIGHT - sHeight - (int) transY, sWeight, sHeight, null);
        }
        else if(editorKeyListener.getObjectType() == EditorKeyListener.STRUCTURE){
            Structure2D structure2D = Structure2D.getStructure(editorKeyListener.getSelected());
            assert structure2D != null;
            g2d.drawImage(map.graphicsHandler.getImage(structure2D.toString()), WIDTH - sWeight - (int)transX, HEIGHT - sHeight - (int) transY, sWeight, sHeight, null);
        }


    }

    private void setTranslation(int rightWise, int upWise) {
        if (upWise > 0) transY += 2;
        if (upWise < 0) transY -= 2;

        if (rightWise > 0) transX += 2;
        if (rightWise < 0) transX -= 2;
    }

    private void setScale(double scale) {
        if (scale == 0) return;
        final int precision = 20;
        this.scale += scale / precision;
    }

    @Override
    public void update() {
        setTranslation(cameraListener.getTranslateX(), cameraListener.getTranslateY());
        setScale(cameraListener.getScale());

        point.x = (int) (mouseGameListener.getX() - transX);
        point.y = (int) (mouseGameListener.getY() - transY);

        /* EDITING */
        if (mouseGameListener.isClicked()) {
            for (int i = 0; i < map.getHEIGHT(); i++) {
                for (int j = 0; j < map.getWIDTH(); j++) {
                    int y = map.titles[i][j].getY();
                    if (y + sHeight < point.y) break;

                    int x = map.titles[i][j].getX();
                    if (map.titles[i][j].getRectange(sWeight, sHeight).contains(point)) {
                        if (editorKeyListener.getObjectType() == EditorKeyListener.GROUND)
                            map.titles[i][j] = Title.getTitle(editorKeyListener.getSelected(), x, y);
                        else if (editorKeyListener.getObjectType() == EditorKeyListener.STRUCTURE)
                            map.titles[i][j].addStructure(Structure2D.getStructure(editorKeyListener.getSelected()));
                    }
                }
            }
        }
            /* EXITING */
            if (exitListener.isEscaped() || exiting) {
                System.out.println("dziala cos help");
                if (!exiting) {
                    exitingEditorPane = new ExitingEditorPane();
                    exitingEditorPane.setVisible(true);
                    exiting = true;
                }
                if (exitingEditorPane == null && !exiting) {
                    status = -1;
                    return;
                }
                assert exitingEditorPane != null;
                if (exitingEditorPane.isReady()) {
                    map.setName(exitingEditorPane.getNameValue());
                    map.setAutor(exitingEditorPane.getAutorValue());
                    map.setDescription(exitingEditorPane.getDescriptionValue());
                    exitingEditorPane.exit();
                    exitingEditorPane = null;
                    new Thread(() -> {
                        try {
                            map.saveMap(map);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).run();
                    status = -1;
                    exiting = false;
                    return;
                }
            }


    }

    @Override
    public void draw(){
        repaint();
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public int getOptionalStatus () {
        return optionalStatus;
    }

}