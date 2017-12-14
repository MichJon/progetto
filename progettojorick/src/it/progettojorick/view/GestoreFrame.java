package it.progettojorick.view;

import it.progettojorick.business.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GestoreFrame extends JFrame{

    private int x=500;
    private int y=150;

    public GestoreFrame(){
        super("finestra gestore");

        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2-100);

        c.setLayout(new BorderLayout());

        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());

        GestoreFrame _this=this;

        JButton inserisciProdotto=new JButton("Inserisci Prodotto");
        sud.add(inserisciProdotto);


        inserisciProdotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.setVisible(false);
                ProdottoFrame p = new ProdottoFrame();
                SessionManager.getInstance().getSession().put("finestra_prodotto",p);
            }
        });


//        JButton sfoglia = new JButton("Sfoglia");
//        sfoglia.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new FileChooser();
//            }
//        });
//
//        c.add(sfoglia);
        c.add(sud, BorderLayout.SOUTH);
        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
