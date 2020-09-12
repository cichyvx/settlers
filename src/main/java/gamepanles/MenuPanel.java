package gamepanles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements GamePanel2D{

    private final int WIDTH, HEIGHT;
    private int status = 0,optionalStatus;

    private JButton[] buttons;
    private final String[] buttonsText = {"Start Game", "Map Editor", "Optios", "Exit"};

    public MenuPanel(int width, int height){
        optionalStatus = 0;
        WIDTH = width;
        HEIGHT = height;
        this.setSize(width, height);
        this.setVisible(true);
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

    private void setStatus(int status){
        this.status = status;
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//    }

    @Override
    public void update() {

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
