package it.progettojorick.view;

import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.GestoreCatalogo;
import it.progettojorick.model.Prodotto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaProdottiFrame extends JFrame {                            ///////aggiungere modifica prodotto in DAO, business e qui
                                                                            ///////così il gestore può modificare il prodotto e la disponibilità
    int x = 1024;
    int y = 700;

    public ListaProdottiFrame() {
        super("Finestra lista prodotti");
        getContentPane().setLayout(new BorderLayout());


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - x / 2, (dim.height / 2) - y / 2);

        GestoreCatalogo g = (GestoreCatalogo) SessionManager.getInstance().getSession().get("gestore");
//        ArrayList<Prodotto> listaprodotti = ProdottoBusiness.getInstance().prodottiPresenti();


        ListaProdottiTableModel lptm = new ListaProdottiTableModel();//listaprodotti);

        JTable listaProd = new JTable(lptm);
        getContentPane().add(new JScrollPane(listaProd), BorderLayout.CENTER);


        getContentPane().add(new JLabel("BENVENUTO " + g.getNome() + " " + g.getCognome() + "!"), BorderLayout.NORTH);
        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());

        JButton indietro = new JButton("Indietro");
        sud.add(indietro);
        JButton btnLogout = new JButton("Logout");
        sud.add(btnLogout);
        JButton btnAggiungi = new JButton("Aggiungi");
        sud.add(btnAggiungi);
//        JButton btnRimuovi = new JButton("Rimuovi");
//        sud.add(btnRimuovi);
        JButton btnCreaProdComp = new JButton("Crea prodotto composto");
        sud.add(btnCreaProdComp);

        ListaProdottiFrame _this = this;

        btnAggiungi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    ProdottoFrame p = new ProdottoFrame(null);
                    _this.setVisible(false);
                    SessionManager.getInstance().getSession().put("finestra_prodotto",p);
                }catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(null,"Inserire prima almeno una categoria.");

                }


            }
        });

//        btnRimuovi.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                //RIMOZIONE E NUOVA QUERY NEL DAO PER RIMUOVERE
//                //REFRESH
//                int index = listaProd.getSelectedRow();
//
//                try {
//                    String nome = (String) listaProd.getModel().getValueAt(index, 0);
//
//                    try {
//                        ProdottoBusiness.getInstance().rimuoviProdotto(nome);
//                        JOptionPane.showMessageDialog(null, "Prodotto rimosso.");
//                    }catch (Exception ex){
//                        JOptionPane.showMessageDialog(null,"Impossibile rimuovere il prodotto finchè tutti i carrelli non lo avranno in uso e gli ordini non saranno chiusi.");
//                    }
//                    _this.dispose();
//                    new ListaProdottiFrame();
//                }catch (Exception ex){
//                    JOptionPane.showMessageDialog(null,"Seleziona prodotto da rimuovere");
//                }
//            }
//        });

        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                SessionManager.getInstance().getSession().put("finestra_gestore",g);
                new GestoreFrame();
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

        btnCreaProdComp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> prodotti = new ArrayList<String>();
                int[] righeSelezionate = listaProd.getSelectedRows();
                if(righeSelezionate.length>1){

                    for (int i = 0; i < righeSelezionate.length; i++) {
                        int rowCount = righeSelezionate[i];
                        String prodottoContenuto = (String) listaProd.getModel().getValueAt(rowCount, 0);
                        prodotti.add(prodottoContenuto);
                    }

                    ArrayList<Prodotto> prodottiContenuti = new ArrayList<Prodotto>();

                    Iterator j = prodotti.iterator();

                    while (j.hasNext()) {

                        String nomeProdotto = (String)j.next();

                        Prodotto p = ProdottoBusiness.getInstance().trovaProdotto(nomeProdotto);

                        prodottiContenuti.add(p);
                    }

                    _this.dispose();
                    new ProdottoFrame(prodottiContenuti);

                }
                else {
                    JOptionPane.showMessageDialog(null, "Selezionare 2 o più prodotti tenendo premuto Ctrl (o Cmd su Mac) e cliccare col mouse.");
                }
            }


        });

        getContentPane().add(sud, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x, y);
        setVisible(true);


    }
}
