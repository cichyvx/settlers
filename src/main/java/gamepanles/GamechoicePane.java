package gamepanles;

import gamepanles.panelListeners.ExitListener;

import javax.swing.*;
import java.awt.*;
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

    public GamechoicePane(){
        this.status = 0;
        this.optionalStatus = 0;
        selected = 0;

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
        });

        for(int i = 0; i < choiceButtons.length - 1; i++){
            choiceButtons[i] = new JRadioButton(files[i].getName());
            final int c = i;
            choiceButtons[i].addActionListener(e -> {
                selected = c;
                for (int j = 0; j < choiceButtons.length; j++)
                    choiceButtons[j].setSelected(false);
                choiceButtons[c].setSelected(true);
            });
        }

        choiceButtons[choiceButtons.length - 1].setSelected(true);

        button = new JButton("Start");
        button.addActionListener(e -> {
            status = 1;
        });

        for(int i = 0; i < choiceButtons.length; i++)
            this.add(choiceButtons[i]);
        this.add(button);

        setVisible(true);

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
