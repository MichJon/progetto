package it.progettojorick.view;

import it.progettojorick.business.AmministratoreBusiness;
import it.progettojorick.dao.mysql.RichiestaRegistrazioneDAO;
import it.progettojorick.model.Amministratore;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.RichiestaRegistrazione;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;


public class RichiestaRegistrazioneFrame extends JFrame {

    public RichiestaRegistrazioneFrame() {
        super("Finestra amministratore");
        getContentPane().setLayout(new BorderLayout());

        Amministratore a = (Amministratore)SessionManager.getInstance().getSession().get("amministratore");
        ArrayList<RichiestaRegistrazione> richieste = RichiestaRegistrazioneDAO.getInstance().findAll();



            //getContentPane().add(new JLabel("area di lavoro"), BorderLayout.CENTER);

            String[][] data = new String[3][3];
//        data[0][0] = "a";
//        data[0][1] = "b";
//        data[0][2] = "c";
//        data[1][0] = "d";
//        data[1][1] = "e";
//        data[1][2] = "f";
//        data[2][0] = "g";
//        data[2][1] = "h";
//        data[2][2] = "i";

            String[] columnNames = new String[]{"Id richiesta", "Amministratore", "Email utente", "Stato"};

            TableModel tm = new DefaultTableModel(data, columnNames);

            RichiesteRegistrazioneTableModel rrtm = new RichiesteRegistrazioneTableModel(richieste);

            JTable richiesteReg = new JTable(rrtm);
            getContentPane().add(new JScrollPane(richiesteReg), BorderLayout.CENTER);


            getContentPane().add(new JLabel("BENVENUTO " + a.getNome() + " " + a.getCognome() + "!"), BorderLayout.NORTH);
            JPanel sud = new JPanel();
            sud.setLayout(new FlowLayout());
            JButton btnLogout = new JButton("Logout");
            sud.add(btnLogout);
            JButton btnConfermaRichieste = new JButton("Conferma");
            sud.add(btnConfermaRichieste);
            JButton btnNegaRichieste = new JButton("Nega");
            sud.add(btnNegaRichieste);
            getContentPane().add(sud, BorderLayout.SOUTH);

            RichiestaRegistrazioneFrame _this = this;
            btnLogout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    SessionManager.getInstance().getSession().put("amministratore", null);
                    _this.setVisible(false);
                    LoginFrame finestraLogin = new LoginFrame();
                    SessionManager.getInstance().getSession().put("finestra_login", finestraLogin);
                }
            });

            btnConfermaRichieste.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // int columnIndex = richiesteReg.getSelectedColumn();
                    ArrayList<String> email = new ArrayList<String>();
                    int[] righeSelezionate = richiesteReg.getSelectedRows();
                    for (int i = 0; i < righeSelezionate.length; i++) {
                        int rowCount = righeSelezionate[i];
                        String mail = (String) richiesteReg.getModel().getValueAt(rowCount, 2);
                        email.add(mail);
                    }

                    Iterator j = email.iterator();

                    while (j.hasNext()) {

                        String richiesta = (String) j.next();

                        AmministratoreBusiness.getInstance().confermaRichiesta(richiesta);

                    }
                    _this.setVisible(false);
                    SessionManager.getInstance().getSession().put("finestra_richieste_registrazione", new RichiestaRegistrazioneFrame());

                }

            });

            btnNegaRichieste.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    ArrayList<String> email = new ArrayList<String>();
                    int[] righeSelezionate = richiesteReg.getSelectedRows();
                    for (int i = 0; i < righeSelezionate.length; i++) {
                        int rowCount = righeSelezionate[i];
                        String mail = (String) richiesteReg.getModel().getValueAt(rowCount, 2);
                        email.add(mail);
                    }

                    Iterator j = email.iterator();

                    while (j.hasNext()) {

                        String richiesta = (String) j.next();

                        AmministratoreBusiness.getInstance().negaRichiesta(richiesta);

                    }
                    _this.setVisible(false);
                    try {
                        SessionManager.getInstance().getSession().put("finestra_richieste_registrazione", new RichiestaRegistrazioneFrame());
                    }catch(Exception ex){
                        System.out.println("error");
                        JOptionPane.showMessageDialog(null, "Sono terminate le richieste di registrazione.");
                        AmministratoreFrame ammFr= new AmministratoreFrame();
                        SessionManager.getInstance().getSession().put("finestra_amministratore", ammFr);

                    }
                }

            });

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1024,700);
            setVisible(true);

//        else{
//
//            JOptionPane.showMessageDialog(null, "Non sono presenti richieste di registrazione");
//
//        }


    }
}
