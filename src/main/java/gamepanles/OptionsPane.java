package gamepanles;

import core.App;
import gamepanles.panelListeners.ExitListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OptionsPane extends DefaultPanel{

    private final int WIDTH, HEIGHT;
    private ExitListener exitListener;

    private JButton fullscsreen, dimension;

    public OptionsPane(int width, int height){
        this.WIDTH = width;
        this.HEIGHT = height;
        exitListener = new ExitListener();
        addKeyListener(exitListener);

        fullscsreen = new JButton("Fullscrean: " + String.valueOf(App.isFullscrean()));
        dimension = new JButton("Resoluton: "+App.getDimension().width+" x "+App.getDimension().height);

        fullscsreen.addActionListener( (e) ->{
            this.requestFocus();
            setOptionalStatus(1);
            fullscsreen.revalidate();
            dimension.revalidate();
            fullscsreen.setText("Fullscrean: " + String.valueOf(App.isFullscrean()));
            dimension.setText("Resoluton: "+App.getDimension().width+" x "+App.getDimension().height);
        });

        dimension.addActionListener((e) -> {
            this.requestFocus();
            setOptionalStatus(2);
            fullscsreen.revalidate();
            dimension.revalidate();
            fullscsreen.setText("Fullscrean: " + String.valueOf(App.isFullscrean()));
            dimension.setText("Resoluton: "+App.getDimension().width+" x "+App.getDimension().height);
        });

        this.add(dimension);
        this.add(fullscsreen);

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
        if(exitListener.isEscaped()) setStatus(-1);

        fullscsreen.setText(String.valueOf(App.isFullscrean()));
    }

    @Override
    public void draw() {
        repaint();
    }

}
