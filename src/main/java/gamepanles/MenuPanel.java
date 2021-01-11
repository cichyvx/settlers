package gamepanles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends DefaultPanel{

    private final int WIDTH, HEIGHT;

    private Image wallpaper;

    private JButton[] buttons;
    private final String[] buttonsText = {"Start Game", "Map Editor", "Optios", "Exit"};

    public MenuPanel(int width, int height){
        super();
        WIDTH = width;
        HEIGHT = height;
        this.setSize(width, height);
        this.setVisible(true);

        try {
            wallpaper = ImageIO.read(new File("resources\\app\\mainScreen\\mainWallpaper.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //this.setLayout(new BorderLayout());
        buttons = new JButton[buttonsText.length];

//        if(buttons.length != buttonsText.length){
//            System.err.println("buttons text and buttons don't have same length");
//            throw new ExceptionInInitializerError();
//        }

        for(short i = 0; i < buttons.length; i++){
            buttons[i] = new JButton(buttonsText[i]);
            final short finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(finalI);
                    if(finalI + 1 >= buttons.length)
                        setStatus(-1);
//                    if(finalI == 3){
//                        optionalStatus = 3;
//                        System.out.println("bingo");
//                    }
                    else {
                        setStatus(finalI + 1);
                    }
                }
            });
            this.add(buttons[i]);
        }


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(wallpaper, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        repaint();
    }

}
