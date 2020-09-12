package gamepanles.panelListeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitingEditorPane extends JFrame {

    private final JLabel autorLabel, descriptionLabel, nameLabel;
    private JTextField autorText, descriptionText, nameText;
    private JButton button;
    private boolean ready;

    public ExitingEditorPane(){

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(100,300);
        this.setResizable(false);

        autorLabel = new JLabel("Autor: ");
        descriptionLabel = new JLabel("Description: ");
        nameLabel = new JLabel("Map Name: ");

        autorText = new JTextField();
        descriptionText = new JTextField();
        nameText = new JTextField();

        button = new JButton("save");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ready = true;
                System.out.println(isReady());
            }
        });

        this.setLayout(new GridLayout(4,2));

        this.add(autorLabel);
        this.add(autorText);

        this.add(descriptionLabel);
        this.add(descriptionText);

        this.add(nameLabel);
        this.add(nameText);

        this.add(button);

    }

    public boolean isReady() {
        return ready;
    }

    public String getNameValue() {return nameText.getText();}

    public String getAutorValue() {return autorText.getText();}

    public String getDescriptionValue() {return descriptionText.getText();}


    public void exit(){
        this.dispose();
    }
}
