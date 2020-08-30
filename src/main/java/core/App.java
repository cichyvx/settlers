package core;

import gamepanles.*;

import javax.swing.*;
import java.awt.*;

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

    private int status;
    public final static int WIDTH = 1280, HEIGHT = 720;
    private final static String TITLE = "Settler game";
    public static final Dimension appDim = new Dimension(WIDTH, HEIGHT);
    private final short updateTime = 15;
    private GamePanel gamePanel;
    private EditorPane editorPanel;
    private OptionsPane optionPane;
    private MenuPanel menuPanel;
    public static boolean RUNNING;


    public static void main(String[] args){
        Thread mainThread = new Thread(new App());
        mainThread.start();
    }

    public App(){
        status = 0;
        RUNNING = true;
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
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
    }

    private void removeC(){
        switch (this.status){
            case 0:
                remove(menuPanel);
                menuPanel = null;
                break;
            case 1:
                remove(gamePanel);
                gamePanel = null;
                break;
            case 2:
                remove(editorPanel);
                editorPanel = null;
                break;
            case 3:
                remove(optionPane);
                optionPane = null;
                break;
        }
    }

    private void changeStatus(int status){
        removeC();
        switch (status){
            case 0:
                this.status = 0;
                menuPanel = new MenuPanel(this.getWidth(), this.getHeight());
                this.add(menuPanel).requestFocus();
                break;
            case 1:
                this.status = 1;
                this.gamePanel = new GamePanel(this.getWidth(), this.getHeight());
                this.add(gamePanel).requestFocus();
                break;
            case 2:
                this.status = 2;
                this.editorPanel = new EditorPane(this.getWidth(), this.getHeight());
                this.add(editorPanel).requestFocus();
                break;
            case 3:
                this.status = 3;
                this.optionPane = new OptionsPane(this.getWidth(), this.getHeight());
                this.add(optionPane).requestFocus();
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
        if (menuPanel.getStatus() != 0) {
            changeStatus(menuPanel.getStatus());
        }
    }

    // state 1
    private void gameUpdate(){
        gamePanel.update();
        gamePanel.draw();
        if(gamePanel.getStatus() == -1){
            //gamePanel = null;
            changeStatus(0);
        }
    }

    // state 2
    private void editorUpdate(){
        editorPanel.update();
        editorPanel.draw();
        if(editorPanel.getStatus() == -1){
            //editorPanel = null;
            changeStatus(0);
        }
    }

    // state 3
    private void optiopUpdate(){
        optionPane.update();
        optionPane.draw();
        if(optionPane.getStatus() == -1){
            //optionPane = null;
            changeStatus(0);
        }
    }

    private void update() {
        switch (status){
            case 0:
                menuUpdate();
                break;
            case 1:
                gameUpdate();
                break;
            case 2:
                editorUpdate();
                break;
            case 3:
                optiopUpdate();
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