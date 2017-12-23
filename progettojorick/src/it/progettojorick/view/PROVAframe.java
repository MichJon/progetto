package it.progettojorick.view;

import javax.swing.*;
import java.awt.*;

public class PROVAframe extends JFrame {
    private int x = 400;
    private int y = 350;
    public PROVAframe(){
        super("prova");

        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        c.setLayout(new BorderLayout());







        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);

    }
}
