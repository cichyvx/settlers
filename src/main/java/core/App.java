package core;

import gamepanles.*;
import map.Map;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class App extends JFrame implements Runnable{

    private final int MENU = 0, GAME = 5, MAPSETTING = 2, OPTION = 3, EDITOR = 4, GAMECHOICE = 1;

    private static int dimension;
    public static final int sWidth = 25, sHeight = 25;

    private int status, optionalStatus;
    private final static String TITLE = "Settler game";
    public static final Dimension[] dimensins = new Dimension[3];
    private GamePanel gamePanel;
    private EditorPane editorPanel;
    private OptionsPane optionPane;
    private MenuPanel menuPanel;
    private MapSettingPanel mapSettingPanel;
    private GamechoicePane gamechoicePane;
    public static boolean RUNNING;
    private final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    private int mapWidth, mapHeight, nRiver, nRock;
    private String mapName;

    public static boolean isFullscrean() {return fullscrean;}

    public static Dimension getDimension(){
        return isFullscrean()? Toolkit.getDefaultToolkit().getScreenSize() : dimensins[dimension];
    }

    private void nextDim(){
        fullscrean = true;
        changeFullScrean();
        if(dimension + 1 < dimensins.length)
            dimension++;
        else
            dimension = 0;

        this.setSize(dimensins[dimension]);
        changeStatus(OPTION);

    }


    public static void main(String[] args)  {
        Thread mainThread = new Thread(new App());
        mainThread.start();
    }

    private void config(){
        fullscrean = true;

        dimension = 0;
        dimensins[0] = new Dimension(1280, 720);
        dimensins[1] = new Dimension(1600, 1280);
        dimensins[2] = new Dimension(640, 360);

    }


    public static boolean fullscrean;

    private void changeFullScrean(){
        if(fullscrean) {
            device.setFullScreenWindow(null);
            fullscrean = false;
            this.setSize(dimensins[dimension]);
            return;
        }
        else{
            device.setFullScreenWindow(this);
            fullscrean = true;
        }
        changeStatus(OPTION);
    }

    public App(){
        System.setProperty("sun.java2d.opengl", "true");
        status = 0;
        RUNNING = true;

        this.setResizable(false);
        this.setTitle(TITLE);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.menuPanel = new MenuPanel(this.getWidth(), this.getHeight());
        this.add(menuPanel);
        pack();
        device.setFullScreenWindow(this);
        config();
    }

    private void removeC(){
        switch (this.status) {
            case MENU -> {
                remove(menuPanel);
                menuPanel = null;
            }
            case GAME -> {
                remove(gamePanel);
                gamePanel = null;
            }
            case MAPSETTING -> {
                remove(mapSettingPanel);
                mapWidth = mapSettingPanel.getMapWidth();
                mapHeight = mapSettingPanel.getMapHeight();
                nRiver = mapSettingPanel.getRiverCount();
                nRock = mapSettingPanel.getRockCount();
                mapSettingPanel = null;
            }
            case OPTION -> {
                remove(optionPane);
                optionPane = null;
            }
            case EDITOR -> {
                remove(editorPanel);
                editorPanel = null;
            }
            case GAMECHOICE -> {
                remove(gamechoicePane);
                mapWidth = gamechoicePane.getMapSize();
                mapHeight = gamechoicePane.getMapSize();
                mapName = gamechoicePane.getMapName();
                gamechoicePane = null;
            }
        }
    }

    private void changeStatus(int status){
        removeC();

        switch (status) {
            case MENU -> {
                this.status = MENU;
                menuPanel = new MenuPanel(this.getWidth(), this.getHeight());
                this.add(menuPanel).requestFocus();
            }

            case GAME -> {
                this.status = GAME;
                if (mapName.equals("")) {
                    Map map = new Map(mapWidth, mapHeight, sWidth, sHeight, new Random().nextInt(2) + 2, new Random().nextInt(2) + 2);
                    this.gamePanel = new GamePanel(this.getWidth(), this.getHeight(), sWidth, sHeight, map);
                } else
                    this.gamePanel = new GamePanel(this.getWidth(), this.getHeight(), sWidth, sHeight, Map.loadMap(mapName));
                this.add(gamePanel).requestFocus();
            }

            case MAPSETTING -> {
                this.status = MAPSETTING;
                this.mapSettingPanel = new MapSettingPanel(optionalStatus);
                this.add(mapSettingPanel).requestFocus();
            }

            case OPTION -> {
                this.status = OPTION;
                this.optionPane = new OptionsPane(this.getWidth(), this.getHeight());
                this.add(optionPane).requestFocus();
            }

            case EDITOR -> {
                this.status = EDITOR;
                this.editorPanel = new EditorPane(this.getWidth(), this.getHeight(), mapWidth, mapHeight, nRiver, nRock);
                this.add(editorPanel).requestFocus();
            }

            case GAMECHOICE -> {
                this.status = GAMECHOICE;
                this.gamechoicePane = new GamechoicePane();
                this.add(gamechoicePane).requestFocus();
            }

            case -1 -> System.exit(0);
        }
        this.revalidate();
    }

    // state 0
    private void menuUpdate(){
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        menuPanel.update();
        menuPanel.repaint();

        int menuStatus = menuPanel.getStatus();
        int menuOptional = menuPanel.getOptionalStatus();

        if (menuStatus != 0) {
            optionalStatus = menuOptional;
            changeStatus(menuStatus);
        }

    }

    /* STAGE 1 */
    private void gameUpdate(){
        gamePanel.update();
        gamePanel.draw();

        if(gamePanel.getStatus() == -1)
            changeStatus(MENU);
    }

    /* STAGE 2 */
    private void editorUpdate(){
        editorPanel.update();
        editorPanel.draw();

        if(editorPanel.getStatus() == -1)
            changeStatus(MENU);
    }

    /* STAGE 3 */
    private void optiopUpdate(){
        optionPane.update();
        optionPane.draw();

        int optionOptional = optionPane.getOptionalStatus();

        if(optionPane.getStatus() == -1)
            changeStatus(MENU);

        if(optionOptional == 1)
            changeFullScrean();

        else if(optionOptional == 2)
            nextDim();

        else if(optionOptional == 3){

        }
    }

    private void mapSettingUpdate(){
        mapSettingPanel.update();
        mapSettingPanel.draw();

        if(mapSettingPanel.getStatus() == 1)
            changeStatus(EDITOR);

        else if(mapSettingPanel.getStatus() == -1)
            changeStatus(MENU);
    }

    private void gamechoicePaneUpdate(){
        gamechoicePane.update();
        gamechoicePane.draw();

        if(gamechoicePane.getStatus() == 1)
            changeStatus(GAME);

        else if(gamechoicePane.getStatus() == -1)
            changeStatus(MENU);
    }

    private void update() {
        switch (status) {
            case MENU -> menuUpdate();
            case GAME -> gameUpdate();
            case MAPSETTING -> mapSettingUpdate();
            case OPTION -> optiopUpdate();
            case EDITOR -> editorUpdate();
            case GAMECHOICE -> gamechoicePaneUpdate();
            case -1 -> System.exit(0);
        }
    }

    @Override
    public void run() {
        short updateTime = 10;
        while (RUNNING){
            update();
            setBackground(Color.black);

            try {
                Thread.sleep(updateTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}