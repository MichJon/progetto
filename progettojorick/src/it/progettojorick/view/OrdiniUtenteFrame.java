package it.progettojorick.view;

import it.progettojorick.business.RichiestaOrdineBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.RichiestaOrdine;
import it.progettojorick.model.Utente;
//import sun.plugin2.os.windows.FLASHWINFO;
import utilities.PDF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class OrdiniUtenteFrame extends JFrame {

    int x = 1024;
    int y = 700;

    public OrdiniUtenteFrame(){

        super("Finestra gestione ordini (utente)");

        getContentPane().setLayout(new BorderLayout());

        OrdiniUtenteFrame _this = this;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - x / 2, (dim.height / 2) - y / 2);

        Utente u = (Utente) SessionManager.getInstance().getSession().get("utente");
        ArrayList<RichiestaOrdine> richiesteOrdine = RichiestaOrdineBusiness.getInstance().richiesteOrdineUtente(u);
        JPanel centro = new JPanel(new FlowLayout());

        OrdiniTableModel otm = new OrdiniTableModel(richiesteOrdine);

        JTable richiesteOrd = new JTable(otm);

        if (richiesteOrdine!=null) {

            getContentPane().add(new JScrollPane(richiesteOrd), BorderLayout.CENTER);
        }
        else {
            centro.add(new JLabel("Non sono presenti ordini."));
            getContentPane().add(centro, BorderLayout.CENTER);
        }
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

        JButton btnPDF= new JButton("Salva ricevuta PDF");
        btnPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                int index = richiesteOrd.getSelectedRow();
                if (index != -1) {
                    id = (Integer) richiesteOrd.getModel().getValueAt(index, 0);
                    try {
                        PDF.getInstance().creaPDF(id);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Errore.");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Selezionare un ordine.");
                }
            }
        });


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
