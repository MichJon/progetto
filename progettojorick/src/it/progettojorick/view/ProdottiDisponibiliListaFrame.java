package it.progettojorick.view;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import it.progettojorick.business.PaniereBusiness;
import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.GestoreCatalogo;
import it.progettojorick.model.Paniere;
import it.progettojorick.model.Prodotto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProdottiDisponibiliListaFrame extends JFrame {

    int x = 1024;
    int y = 700;

    public ProdottiDisponibiliListaFrame() {
        super("Finestra lista prodotti");
        getContentPane().setLayout(new BorderLayout());

        Paniere pan = (Paniere) SessionManager.getInstance().getSession().get("paniere");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - x / 2, (dim.height / 2) - y / 2);

        //GestoreCatalogo g = (GestoreCatalogo) SessionManager.getInstance().getSession().get("gestore");
        ArrayList<Prodotto> listaprodotti = PaniereBusiness.getInstance().prodottiContenuti(pan);


        ListaProdottiTableModel lptm = new ListaProdottiTableModel();//listaprodotti);

        JTable listaProd = new JTable(lptm);
        getContentPane().add(new JLabel("Selezionare prodotto da aggiugere al paniere:"), BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(listaProd), BorderLayout.CENTER);


       // getContentPane().add(new JLabel("BENVENUTO " + g.getNome() + " " + g.getCognome() + "!"), BorderLayout.NORTH);
        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
//        JButton btnLogout = new JButton("Logout");
//        sud.add(btnLogout);
        JButton btnIndietro = new JButton("Indietro");
        sud.add(btnIndietro);
        JButton btnAggiungi = new JButton("Aggiungi prodotto al paniere");
        sud.add(btnAggiungi);


        ProdottiDisponibiliListaFrame _this = this;

        btnAggiungi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


               int index = listaProd.getSelectedRow();
               if (index!=-1) {
                   String nome = (String) listaProd.getModel().getValueAt(index, 0);
                   PaniereBusiness.getInstance().inserisciProdottoNelPaniere(nome, pan);
                   _this.dispose();
                   new ModificaProdottiPaniereFrame();
               }else JOptionPane.showMessageDialog(null, "Selezionare un prodotto da aggiungere.");
            }
        });

//        JButton btnRimuovi = new JButton("Rimuovi prodotto dal paniere");
//        sud.add(btnRimuovi);
//
//        btnRimuovi.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                //RIMOZIONE E NUOVA QUERY NEL DAO PER RIMUOVERE
//                //REFRESH
//                int index = listaProd.getSelectedRow();
//
//                String nome = (String)listaProd.getModel().getValueAt(index, 0);
//              PaniereBusiness.getInstance().cancellaProdottoDalPaniere(nome,pan);
//              _this.dispose();
//                new ProdottiDisponibiliListaFrame();
//            }
//        });



//        btnLogout.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//              //  SessionManager.getInstance().getSession().put("gestore", null);
//                _this.setVisible(false);
//                LoginFrame finestraLogin = new LoginFrame();
//                SessionManager.getInstance().getSession().put("finestra_login", finestraLogin);
//            }
//        });

        btnIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                _this.dispose();
                new ModificaProdottiPaniereFrame();
            }
        });

        getContentPane().add(sud, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        setVisible(true);


    }
}
