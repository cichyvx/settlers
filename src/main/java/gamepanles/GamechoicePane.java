package gamepanles;

import gamepanles.panelListeners.ExitListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;


public class GamechoicePane extends JPanel implements GamePanel2D{

    final private ExitListener exitListener;
    private int status;
    private final int optionalStatus;
    private final JRadioButton[] choiceButtons;
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

        File[] files = new File("resources\\maps").listFiles();


        if(files != null){
            choiceButtons = new JRadioButton[files.length+1];
            choiceButtons[choiceButtons.length - 1] = new JRadioButton("Generate Random Maps");
        }
        else{
            choiceButtons = new JRadioButton[1];
            choiceButtons[0] = new JRadioButton("Generate Random Maps");
        }

        choiceButtons[choiceButtons.length - 1].addActionListener(e -> {
            selected = choiceButtons.length - 1;

            for (JRadioButton choiceButton : choiceButtons)
                choiceButton.setSelected(false);

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

                for (JRadioButton choiceButton : choiceButtons)
                    choiceButton.setSelected(false);

                choiceButtons[c].setSelected(true);
                sliderMapSize.setEnabled(false);

                this.requestFocus();
            });
        }

        choiceButtons[choiceButtons.length - 1].setSelected(true);

        JButton button = new JButton("Start");
        button.addActionListener(e -> status = 1);

        for (JRadioButton choiceButton : choiceButtons)
            this.add(choiceButton);

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
        for (JRadioButton choiceButton : choiceButtons) {
            if (choiceButton.isSelected())
                return choiceButton.getText();
        }

        return "";
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
