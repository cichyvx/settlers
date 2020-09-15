package core;

import com.sun.tools.javac.Main;
import gamepanles.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class App extends JFrame implements Runnable{

    public enum status{
        GAME(1),
        EDITOR(2),
        OPTIONS(3),
        EXIT(4),
        BACK(-2);

        public final int status;

        status(int status){
            this.status = status;
        }
    }

    private final int MENU = 0, GAME = 5, MAPSETTING = 2, OPTION = 3, EDITOR = 4, GAMECHOICE = 1;

    public static boolean fullscrean;
    private static int dimension;

    private int status,optionalStatus;
    private final static String TITLE = "Settler game";
    public static final Dimension[] dimensins = new Dimension[3];
    private final short updateTime = 15;
    private GamePanel gamePanel;
    private EditorPane editorPanel;
    private OptionsPane optionPane;
    private MenuPanel menuPanel;
    private MapSettingPanel mapSettingPanel;
    private GamechoicePane gamechoicePane;
    public static boolean RUNNING;
    private GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    private int mapWidth, mapHeight, nRiver, nRock;

    public static boolean isFullscrean() {return fullscrean;}

    public static Dimension getDimension(){
        return dimensins[dimension];
    }

    private void nextDim(){
        if(dimension + 1 < dimensins.length)
            dimension++;
        else
            dimension = 0;
        if(!isFullscrean()){
            this.setSize(dimensins[dimension]);
            changeStatus(OPTION);
        }
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
        System.out.println(this.getSize());
        config();
    }

    private void removeC(){
        switch (this.status){
            case MENU:
                remove(menuPanel);
                menuPanel = null;
                break;
            case GAME:
                remove(gamePanel);
                gamePanel = null;
                break;
            case MAPSETTING:
                remove(mapSettingPanel);
                mapWidth = mapSettingPanel.getMapWidth();
                mapHeight = mapSettingPanel.getMapHeight();
                nRiver = mapSettingPanel.getRiverCount();
                nRock = mapSettingPanel.getRockCount();
                mapSettingPanel = null;
                break;
            case OPTION:
                remove(optionPane);
                optionPane = null;
                break;
            case EDITOR:
                remove(editorPanel);
                editorPanel = null;
                break;
            case GAMECHOICE:
                remove(gamechoicePane);
                gamechoicePane = null;
                break;
        }
    }

    private void changeStatus(int status){
        removeC();
        switch (status){
            case MENU:
                this.status = MENU;
                menuPanel = new MenuPanel(this.getWidth(), this.getHeight());
                this.add(menuPanel).requestFocus();
                break;
            case GAME:
                this.status = GAME;
                this.gamePanel = new GamePanel(this.getWidth(), this.getHeight());
                this.add(gamePanel).requestFocus();
                break;
            case MAPSETTING:
                this.status = MAPSETTING;
                this.mapSettingPanel = new MapSettingPanel(optionalStatus);
                this.add(mapSettingPanel).requestFocus();
                break;
            case OPTION:
                this.status = OPTION;
                this.optionPane = new OptionsPane(this.getWidth(), this.getHeight());
                this.add(optionPane).requestFocus();
                break;
            case EDITOR:
                this.status = EDITOR;
                this.editorPanel = new EditorPane(this.getWidth(), this.getHeight(), mapWidth, mapHeight, nRiver, nRock);
                this.add(editorPanel).requestFocus();
                break;
            case GAMECHOICE:
                this.status = GAMECHOICE;
                this.gamechoicePane = new GamechoicePane();
                this.add(gamechoicePane).requestFocus();
                break;
            case -1:
                System.exit(0);
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
            System.out.println("option status: " + menuStatus);
            optionalStatus = menuOptional;
            changeStatus(menuStatus);
        }

    }

    // state 1
    private void gameUpdate(){
        gamePanel.update();
        gamePanel.draw();
        if(gamePanel.getStatus() == -1){
            //gamePanel = null;
            changeStatus(MENU);
        }
    }

    // state 2
    private void editorUpdate(){
        editorPanel.update();
        editorPanel.draw();
        if(editorPanel.getStatus() == -1){
            //editorPanel = null;
            changeStatus(MENU);
        }
    }

    // state 3
    private void optiopUpdate(){
        optionPane.update();
        optionPane.draw();
        int optionOptional = optionPane.getOptionalStatus();
        if(optionPane.getStatus() == -1){
            //optionPane = null;
            changeStatus(MENU);
        }
        if(optionOptional == 1){
            changeFullScrean();
        }
        else if(optionOptional == 2){
            nextDim();
        }
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
        switch (status){
            case MENU:
                menuUpdate();
                break;
            case GAME:
                gameUpdate();
                break;
            case MAPSETTING:
                mapSettingUpdate();
                break;
            case OPTION:
                optiopUpdate();
                break;
            case EDITOR:
                editorUpdate();
                break;
            case GAMECHOICE:
                gamechoicePaneUpdate();
                break;
            case -1:
                System.exit(0);
        }
    }

    @Override
    public void run() {
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