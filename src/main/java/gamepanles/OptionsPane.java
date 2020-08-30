package gamepanles;

import core.App;
import gamepanles.panelListeners.ExitListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OptionsPane extends JPanel implements GamePanel2D {

    private final int WIDTH, HEIGHT;
    private int status,optionalStatus;
    private ExitListener exitListener;

    private JButton fullscsreen;

    public OptionsPane(int width, int height){
        optionalStatus = 0;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.status = 0;
        this.optionalStatus = 0;
        exitListener = new ExitListener();
        addKeyListener(exitListener);

        fullscsreen = new JButton(String.valueOf(App.isFullscrean()));
        fullscsreen.addActionListener( (e) ->{
            this.requestFocus();
            optionalStatus = 1;
        });

        this.add(fullscsreen);

    }

    @Override
    public int getOptionalStatus() {
        if(optionalStatus == 0) return optionalStatus;
        final int x = optionalStatus;
        optionalStatus = 0;
        return x;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
        g2d.fillRect(0,0,WIDTH,HEIGHT);
    }

    @Override
    public void update() {
        if(exitListener.isEscaped()) status = -1;

        fullscsreen.setText(String.valueOf(App.isFullscrean()));
    }

    @Override
    public void draw() {
        repaint();
    }

    @Override
    public int getStatus() {
        return status;
    }
}
