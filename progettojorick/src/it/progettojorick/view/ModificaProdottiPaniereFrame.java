package it.progettojorick.view;

import it.progettojorick.business.PaniereBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.dao.mysql.PaniereDAO;
import it.progettojorick.model.Paniere;
import it.progettojorick.model.Prodotto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModificaProdottiPaniereFrame extends JFrame {

    int x = 1024;
    int y = 700;

    public ModificaProdottiPaniereFrame() {
        super("Finestra lista prodotti paniere");
        getContentPane().setLayout(new BorderLayout());


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - x / 2, (dim.height / 2) - y / 2);

        Paniere paniere = (Paniere) SessionManager.getInstance().getSession().get("paniere");
        ArrayList<Prodotto> listaprodotti = PaniereBusiness.getInstance().prodottiContenuti(paniere);


        ListaProdottiTableModel lptm = new ListaProdottiTableModel();//listaprodotti);

        JTable listaProd = new JTable(lptm);
        getContentPane().add(new JScrollPane(listaProd), BorderLayout.CENTER);


        getContentPane().add(new JLabel( paniere.getNome()), BorderLayout.NORTH);
        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
        JButton btnIndietro = new JButton("Indietro");
        sud.add(btnIndietro);
        JButton btnAggiungi = new JButton("Aggiungi");
        sud.add(btnAggiungi);

        ModificaProdottiPaniereFrame _this = this;

        btnAggiungi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                _this.dispose();
                new ProdottiDisponibiliListaFrame();


            }
        });

        JButton btnRimuovi = new JButton("Rimuovi prodotto dal paniere");
        sud.add(btnRimuovi);

        btnRimuovi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //RIMOZIONE E NUOVA QUERY NEL DAO PER RIMUOVERE
                //REFRESH
                int index = listaProd.getSelectedRow();

                String nome = (String)listaProd.getModel().getValueAt(index, 0);
                PaniereBusiness.getInstance().cancellaProdottoDalPaniere(nome,paniere);
                _this.dispose();
                new ModificaProdottiPaniereFrame();
            }
        });



        btnIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              _this.dispose();
              Paniere p = (Paniere)SessionManager.getInstance().getSession().get("paniere");
              Paniere pan = PaniereDAO.getInstance().findById(p.getIdpaniere());

              new ProdottiPaniereFrame(pan.getProdotti());//p.getProdotti());
//                ProdottiPaniereFrame pfr = (ProdottiPaniereFrame)SessionManager.getInstance().getSession().get("paniere_aperto");
//                pfr.setVisible(true);
            }
        });

        getContentPane().add(sud, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        setVisible(true);


    }
}
