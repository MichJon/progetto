package it.progettojorick.view;

import it.progettojorick.business.AmministratoreBusiness;
import it.progettojorick.business.CarrelloBusiness;
import it.progettojorick.business.RichiestaOrdineBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Amministratore;
import it.progettojorick.model.Carrello;
import it.progettojorick.model.RichiestaOrdine;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class OrdiniAmministratoreFrame extends JFrame {

    int x = 1024;
    int y = 700;

    public OrdiniAmministratoreFrame() {
        super("Finestra gestione ordini (amministratore)");
        getContentPane().setLayout(new BorderLayout());

        OrdiniAmministratoreFrame _this = this;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - x / 2, (dim.height / 2) - y / 2);

        Amministratore a = (Amministratore) SessionManager.getInstance().getSession().get("amministratore");
        ArrayList<RichiestaOrdine> richiesteOrdine = RichiestaOrdineBusiness.getInstance().richiestePresenti();


        OrdiniTableModel otm = new OrdiniTableModel(richiesteOrdine);

        JTable richiesteOrd = new JTable(otm);
        getContentPane().add(new JScrollPane(richiesteOrd), BorderLayout.CENTER);


        getContentPane().add(new JLabel("BENVENUTO " + a.getNome() + " " + a.getCognome() + "!"), BorderLayout.NORTH);
        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());

        JButton indietro = new JButton("Indietro");
        sud.add(indietro);
        JButton btnLogout = new JButton("Logout");
        sud.add(btnLogout);
        JButton btnMettiInSpedizione = new JButton("Metti in spedizione");

        btnMettiInSpedizione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> id = new ArrayList<Integer>();
                int[] righeSelezionate = richiesteOrd.getSelectedRows();
                for (int i = 0; i < righeSelezionate.length; i++) {
                    int rowCount = righeSelezionate[i];
                    int idd = (int) richiesteOrd.getModel().getValueAt(rowCount, 0);
                    id.add(idd);
                }

                Iterator j = id.iterator();

                while (j.hasNext()) {
                                                            ////////////EMAIL
                    int idRichiesta = (int) j.next();

                    AmministratoreBusiness.getInstance().ordineInSpedizione(idRichiesta);

                }
                _this.dispose();//setVisible(false);
                SessionManager.getInstance().getSession().put("finestra_gestione_ordini", new OrdiniAmministratoreFrame());

            }


        });
        sud.add(btnMettiInSpedizione);

        JButton btnEvaso = new JButton("Evadi");
        sud.add(btnEvaso);
        btnEvaso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> id = new ArrayList<Integer>();
                int[] righeSelezionate = richiesteOrd.getSelectedRows();
                for (int i = 0; i < righeSelezionate.length; i++) {
                    int rowCount = righeSelezionate[i];
                    int idd = (int) richiesteOrd.getModel().getValueAt(rowCount, 0);
                    id.add(idd);
                }

                Iterator j = id.iterator();

                while (j.hasNext()) {
                    ////////////EMAIL
                    int idRichiesta = (int) j.next();

                    AmministratoreBusiness.getInstance().ordineEvaso(idRichiesta);

                }
                _this.dispose();//setVisible(false);
                SessionManager.getInstance().getSession().put("finestra_gestione_ordini", new OrdiniAmministratoreFrame());

            }


        });
        JButton btnRifiutato = new JButton("Rifiuta");
        sud.add(btnRifiutato);
        btnRifiutato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> id = new ArrayList<Integer>();
                int[] righeSelezionate = richiesteOrd.getSelectedRows();
                for (int i = 0; i < righeSelezionate.length; i++) {
                    int rowCount = righeSelezionate[i];
                    int idd = (int) richiesteOrd.getModel().getValueAt(rowCount, 0);
                    id.add(idd);
                }

                Iterator j = id.iterator();

                while (j.hasNext()) {
                    ////////////EMAIL
                    int idRichiesta = (int) j.next();

                    AmministratoreBusiness.getInstance().ordineRifiutato(idRichiesta);

                }
                _this.dispose();//setVisible(false);
                SessionManager.getInstance().getSession().put("finestra_gestione_ordini", new OrdiniAmministratoreFrame());

            }


        });
        JButton btnVisualizza = new JButton("Visualizza");
        sud.add(btnVisualizza);
        btnVisualizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // _this.setVisible(false);

                int index = richiesteOrd.getSelectedRow();
                try {
                    int idRichiesta = (int) richiesteOrd.getModel().getValueAt(index, 0);
                    RichiestaOrdine r = RichiestaOrdineBusiness.getInstance().trovaRichiesta(idRichiesta);
                    Carrello c = r.getCarrello();
                    new ProdottiOrdineFrame(c);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Seleziona ordine da visualizzare");
                }


            }
        });
        //JButton btnNegaRichieste = new JButton("Nega");
        //sud.add(btnNegaRichieste);
        getContentPane().add(sud, BorderLayout.SOUTH);

       // OrdiniAmministratoreFrame _this = this;
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SessionManager.getInstance().getSession().put("amministratore", null);
                _this.setVisible(false);
//                AmministratoreFrame finestraAmministratore = new AmministratoreFrame();
//                SessionManager.getInstance().getSession().put("finestra_amministratore", finestraAmministratore);

                new LoginFrame();
            }

        });

        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
              //  SessionManager.getInstance().getSession().put("finestra_amministratore",a);
                new AmministratoreFrame();
            }
        });

        //btnModificaStato.addActionListener(new ActionListener() {


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        setVisible(true);

    }
}
