package it.progettojorick.view;

import it.progettojorick.business.SessionManager;
import it.progettojorick.model.GestoreCatalogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GestoreFrame extends JFrame{

    private int x=550;
    private int y=130;

    public GestoreFrame(){
        super("finestra gestore");

        GestoreCatalogo g =(GestoreCatalogo) SessionManager.getInstance().getSession().get("gestore");

        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2-100);

        c.setLayout(new BorderLayout());
        JPanel nord = new JPanel(new FlowLayout());
        JPanel sud = new JPanel(new FlowLayout());
        GridLayout GL = new GridLayout(1,3);
        GL.setHgap(15);
        JPanel Pbuttons = new JPanel(GL);

        GestoreFrame _this=this;

        JLabel benvenuto = new JLabel("Benvenuto gestore "+g.getNome()+"!");
        benvenuto.setFont(new Font("Serif", Font.PLAIN, 25));
        nord.add(benvenuto);
        JButton gestCategorie = new JButton("Gestisci Categorie");
        JButton gestProdotti = new JButton("Gestisci Prodotti");
        JButton btnLogout = new JButton("Logout");
        Pbuttons.add(new JPanel(new FlowLayout()).add(btnLogout));
        Pbuttons.add(new JPanel(new FlowLayout()).add(gestCategorie));
        Pbuttons.add(new JPanel(new FlowLayout()).add(gestProdotti));
        sud.add(Pbuttons);

        gestCategorie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.setVisible(false);
                new ListaCategorieFrame();
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SessionManager.getInstance().getSession().put("gestore", null);
                _this.setVisible(false);

                LoginFrame finestraLogin = new LoginFrame();
                SessionManager.getInstance().getSession().put("finestra_login", finestraLogin);

            }
        });

        gestProdotti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.setVisible(false);
                new ListaProdottiFrame();
            }
        });

        c.add(nord,BorderLayout.NORTH);
        c.add(sud,BorderLayout.SOUTH);
        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
