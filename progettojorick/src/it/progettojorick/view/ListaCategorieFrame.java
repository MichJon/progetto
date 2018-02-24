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

        ListaCategorieFrame _this=this;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - x / 2, (dim.height / 2) - y / 2);

        GestoreCatalogo g = (GestoreCatalogo) SessionManager.getInstance().getSession().get("gestore");
        ArrayList<Categoria> listacategorie = CategoriaBusiness.getInstance().categoriePresenti();


        ListaCategorieTableModel lctm = new ListaCategorieTableModel(listacategorie);

        JTable listaCat = new JTable(lctm);
        getContentPane().add(new JScrollPane(listaCat), BorderLayout.CENTER);


        JLabel intro = new JLabel("Benvenuto " + g.getNome() + " " + g.getCognome() + "!");
        intro.setFont(new Font("Serif", Font.PLAIN, 18));
        getContentPane().add(intro, BorderLayout.NORTH);
        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
        JButton indietro = new JButton("Indietro");
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                SessionManager.getInstance().getSession().put("finestra_gestore",g);
                new GestoreFrame();
            }
        });
        sud.add(indietro);
        JButton btnLogout = new JButton("Logout");
        sud.add(btnLogout);
        JButton btnAggiungi = new JButton("Aggiungi");
        sud.add(btnAggiungi);

        btnAggiungi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                CategoriaFrame c = null;
                try {
                    c = new CategoriaFrame();//exception

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
              //  SessionManager.getInstance().getSession().put("finestra_categoria",new ListaCategorieFrame());
//            new ListaCategorieFrame();
            }
        });

//        JButton btnRimuovi = new JButton("Rimuovi");
//        sud.add(btnRimuovi);
//
//        btnRimuovi.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //RIMOZIONE E NUOVA QUERY NEL DAO PER RIMUOVERE
//                //REFRESH
//                int index = listaCat.getSelectedRow();
//
//                try {
//                    String nome = (String) listaCat.getModel().getValueAt(index, 0);
//                    CategoriaBusiness.getInstance().rimuoviCategoria(nome);
//                    JOptionPane.showMessageDialog(null, "Categoria rimossa.");
//                    _this.dispose();
//                    new ListaCategorieFrame();
//                }catch (Exception ex){
//                    JOptionPane.showMessageDialog(null,"Seleziona categoria da rimuovere");
//                }
//            }
//        });


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
