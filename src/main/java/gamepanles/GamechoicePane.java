package gamepanles;

import gamepanles.panelListeners.ExitListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class GamechoicePane extends JPanel implements GamePanel2D{

    private ExitListener exitListener;
    private int status,optionalStatus;
    private File[] files;
    private JRadioButton[] choiceButtons;
    private JButton button;
    private int selected;
    private JSlider sliderMapSize;
    private int size;

    public GamechoicePane(){
        this.status = 0;
        this.optionalStatus = 0;
        selected = 0;
        size = 50;

        exitListener = new ExitListener();
        this.addKeyListener(exitListener);

        this.setLayout(new GridLayout(40,1));

        files = getFile("maps").listFiles();
        choiceButtons = new JRadioButton[files.length+1];
        choiceButtons[choiceButtons.length - 1] = new JRadioButton("Generate Random Maps");
        choiceButtons[choiceButtons.length - 1].addActionListener(e -> {
            selected = choiceButtons.length - 1;
            for (int j = 0; j < choiceButtons.length; j++)
                choiceButtons[j].setSelected(false);
            choiceButtons[choiceButtons.length - 1].setSelected(true);
            sliderMapSize.setEnabled(true);
            this.requestFocus();
        });
        JLabel label = new JLabel("Set Size: " + size);
        sliderMapSize = new JSlider(JSlider.HORIZONTAL, 25, 1000, size);

        sliderMapSize.addChangeListener(e -> {
            size = sliderMapSize.getValue();
            label.setText("Set Size: " + size);
        });

        sliderMapSize.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                GamechoicePane.super.requestFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {
                GamechoicePane.super.requestFocus();
            }
        });

        for(int i = 0; i < choiceButtons.length - 1; i++){
            choiceButtons[i] = new JRadioButton(files[i].getName());
            final int c = i;
            choiceButtons[i].addActionListener(e -> {
                selected = c;
                for (int j = 0; j < choiceButtons.length; j++)
                    choiceButtons[j].setSelected(false);
                choiceButtons[c].setSelected(true);
                sliderMapSize.setEnabled(false);
                this.requestFocus();
            });
        }

        choiceButtons[choiceButtons.length - 1].setSelected(true);

        button = new JButton("Start");
        button.addActionListener(e -> {
            status = 1;
        });

        for(int i = 0; i < choiceButtons.length; i++)
            this.add(choiceButtons[i]);

        this.add(label);
        this.add(sliderMapSize);
        this.add(button);

        setVisible(true);

    }

    public int getMapSize(){
        return size;
    }

    public String getMapName(){
        if (choiceButtons[choiceButtons.length - 1].isSelected()) return "";
        for (int i = 0; i < choiceButtons.length; i++){
            if (choiceButtons[i].isSelected()) return choiceButtons[i].getText();
        }
        return "";
    }

    private File getFile(String path){
        URL res = this.getClass().getClassLoader().getResource(path);
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return file;
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
