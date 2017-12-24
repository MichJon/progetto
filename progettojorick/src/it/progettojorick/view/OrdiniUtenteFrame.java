package it.progettojorick.view;

import it.progettojorick.business.RichiestaOrdineBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.RichiestaOrdine;
import it.progettojorick.model.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrdiniUtenteFrame extends JFrame {

    int x = 1024;
    int y = 700;

    public OrdiniUtenteFrame(){

        super("Finestra gestione ordini (amministratore)");

        getContentPane().setLayout(new BorderLayout());

        OrdiniUtenteFrame _this = this;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - x / 2, (dim.height / 2) - y / 2);

        Utente u = (Utente) SessionManager.getInstance().getSession().get("utente");
        ArrayList<RichiestaOrdine> richiesteOrdine = RichiestaOrdineBusiness.getInstance().richiesteOrdineUtente(u);


        OrdiniTableModel otm = new OrdiniTableModel(richiesteOrdine);

        JTable richiesteOrd = new JTable(otm);
        getContentPane().add(new JScrollPane(richiesteOrd), BorderLayout.CENTER);

        JPanel sud = new JPanel(new FlowLayout());
        //JPanel sudIndietro = new JPanel(new FlowLayout(FlowLayout.LEFT));//new BorderLayout());
       // JPanel sudPDF = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton indietro = new JButton("Indietro");
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                UtenteFrame utfr = (UtenteFrame) SessionManager.getInstance().getSession().get("finestra_utente");
                utfr.setVisible(true);
            }
        });

        JButton btnPDF= new JButton("Visualizza ricevuta PDF");


       // sudIndietro.add(indietro);
       // sudPDF.add(btnPDF);


        //sud.add(indietro,BorderLayout.WEST);
        sud.add(indietro);
        sud.add(btnPDF);
        getContentPane().add(sud, BorderLayout.SOUTH);
      //  getContentPane().add(sud1,BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        setVisible(true);
    }

}
