package it.progettojorick.view;

import it.progettojorick.business.CategoriaBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Categoria;
import it.progettojorick.model.GestoreCatalogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ListaCategorieFrame extends JFrame {

    int x = 1024;
    int y = 700;

    public ListaCategorieFrame() {
        super("Finestra lista categorie");
        getContentPane().setLayout(new BorderLayout());


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - x / 2, (dim.height / 2) - y / 2);

        GestoreCatalogo g = (GestoreCatalogo) SessionManager.getInstance().getSession().get("gestore");
        ArrayList<Categoria> listacategorie = CategoriaBusiness.getInstance().categoriePresenti();


        ListaCategorieTableModel lctm = new ListaCategorieTableModel(listacategorie);

        JTable listaCat = new JTable(lctm);
        getContentPane().add(new JScrollPane(listaCat), BorderLayout.CENTER);


        getContentPane().add(new JLabel("BENVENUTO " + g.getNome() + " " + g.getCognome() + "!"), BorderLayout.NORTH);
        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
        JButton btnLogout = new JButton("Logout");
        sud.add(btnLogout);
        JButton btnAggiungi = new JButton("Aggiungi");
        sud.add(btnAggiungi);

        ListaCategorieFrame _this = this;

        btnAggiungi.addActionListener(new ActionListener() {
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

        JButton btnRimuovi = new JButton("Rimuovi");
        sud.add(btnRimuovi);

        btnRimuovi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RIMOZIONE E NUOVA QUERY NEL DAO PER RIMUOVERE
                //REFRESH
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

        getContentPane().add(sud, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        setVisible(true);

    }
}