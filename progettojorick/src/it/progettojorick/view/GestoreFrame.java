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

    private int x=500;
    private int y=150;

    public GestoreFrame(){
        super("finestra gestore");

        GestoreCatalogo g =(GestoreCatalogo) SessionManager.getInstance().getSession().get("gestore");

        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2-100);

        c.setLayout(new BorderLayout());

        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
        JPanel nord = new JPanel();



        GestoreFrame _this=this;

        //JButton inserisciProdotto=new JButton("Inserisci Prodotto");
        //sud.add(inserisciProdotto);
        JLabel benvenuto = new JLabel("Benvenuto gestore "+g.getNome()+"!");
        nord.add(benvenuto);
        JButton inserisciCategoria = new JButton("Inserisci Categoria");
        sud.add(inserisciCategoria);



        //inserisciProdotto.addActionListener(new ActionListener() {
         //   @Override
         //   public void actionPerformed(ActionEvent e) {
         //       _this.setVisible(false);
         //       ProdottoFrame p = new ProdottoFrame();
         //       SessionManager.getInstance().getSession().put("finestra_prodotto",p);
          //  }
        //});

        inserisciCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.setVisible(false);
                CategoriaFrame c = null;
                try {
                    c = new CategoriaFrame();               //exception
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                SessionManager.getInstance().getSession().put("finestra_categoria",c);
            }
        });

        JButton btnLogout = new JButton("Logout");
        sud.add(btnLogout);

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SessionManager.getInstance().getSession().put("gestore", null);
                _this.setVisible(false);

                LoginFrame finestraLogin = new LoginFrame();
                SessionManager.getInstance().getSession().put("finestra_login", finestraLogin);

            }
        });

        JButton mostratutti = new JButton("Lista Prodotti");
        sud.add(mostratutti);

        JButton mostracategorie = new JButton("Lista categorie");
        sud.add(mostracategorie);

        c.add(nord, BorderLayout.NORTH);
        c.add(sud, BorderLayout.SOUTH);
        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
