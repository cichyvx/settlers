package gamepanles;

import gamepanles.panelListeners.ExitListener;

import javax.swing.*;
import java.awt.*;

public class MapSettingPanel extends JPanel implements GamePanel2D{

    private JButton applyButton;
    private JSlider widthSlider,heightSlider,riverSlider,rockSlider;
    private JLabel widthText, heightText, riverText, rockText;

    private ExitListener exitListener;

    private int mapWidth, mapHeight, riverCount, rockCount;

    private int status,optionalStatus;

    public MapSettingPanel(int optionalStatus){
        status = 0;
        this.optionalStatus = optionalStatus;
        mapWidth = 50;
        mapHeight = 50;
        riverCount = 3;
        rockCount = 3;

        exitListener = new ExitListener();
        this.addKeyListener(exitListener);

        widthSlider = new JSlider(JSlider.HORIZONTAL, 25, 1000, mapWidth);
        heightSlider = new JSlider(JSlider.HORIZONTAL, 25, 1000, mapHeight);
        riverSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, riverCount);
        rockSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, rockCount);

        widthText = new JLabel("Map Width : " + widthSlider.getValue());
        heightText = new JLabel("Map Height : " + heightSlider.getValue());
        riverText = new JLabel("quantity of river : " + riverSlider.getValue());
        rockText = new JLabel(" quantity of rock : " + rockSlider.getValue());




        applyButton = new JButton("apply");
        applyButton.addActionListener(e -> this.status = 1);

        widthSlider.addChangeListener(e -> widthText.setText("Map Width : " + widthSlider.getValue()));

        heightSlider.addChangeListener(e -> heightText.setText("Map Height : " + heightSlider.getValue()));

        riverSlider.addChangeListener(e -> riverText.setText("quantity of river : " + riverSlider.getValue()));

        rockSlider.addChangeListener(e -> rockText.setText(" quantity of rock : " + rockSlider.getValue()));

        this.setLayout(new GridLayout(20, 2));

        this.add(widthText);
        this.add(widthSlider);

        this.add(heightText);
        this.add(heightSlider);

        this.add(riverText);
        this.add(riverSlider);

        this.add(rockText);
        this.add(rockSlider);

        this.add(applyButton);
    }

    public int getMapWidth(){
        return widthSlider.getValue();
    }

    public int getMapHeight(){
        return heightSlider.getValue();
    }

    public int getRiverCount(){
        return riverCount;
    }

    public int getRockCount(){
        return rockCount;
    }

    @Override
    public void update() {
        if(exitListener.isEscaped()) status = -1;
    }

    @Override
    public void draw() {
        this.repaint();
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
