package it.progettojorick.view;

import it.progettojorick.business.PaniereBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Paniere;
import it.progettojorick.model.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class ElencoPanieriView extends JFrame {

    private int x=1024;
    private int y=700;

    Utente u = (Utente)SessionManager.getInstance().getSession().get("utente");

    public ElencoPanieriView(){

        super("Elenco panieri");

        ElencoPanieriView _this = this;

        SessionManager.getInstance().getSession().put("finestra_elenco_panieri", this);
        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        c.setLayout(new BorderLayout());

        JPanel nord = new JPanel(new FlowLayout());
        JPanel centro = new JPanel(new FlowLayout());
        JPanel sud = new JPanel(new FlowLayout());



        ArrayList<Paniere> panieri= PaniereBusiness.getInstance().trovaPanieriUtente(u);

        if(panieri!=null) {
            Iterator i = panieri.iterator();

            while (i.hasNext()) {

                Paniere p = (Paniere) i.next();

                JButton b = new JButton(p.getNome());
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        _this.setVisible(false);
                        SessionManager.getInstance().getSession().put("paniere",p);
                      // SessionManager.getInstance().getSession().put("nome_paniere_aperto",p.getNome());//p.getProdotti());
                        new ProdottiPaniereFrame(p.getProdotti());
                    }
                });
                centro.add(b);

            }

            nord.add(new JLabel("Panieri salvati:"));
        }
        else{
            centro.add(new JLabel("Non sono presenti panieri."));
        }

        JButton btnIndietro =new JButton("Indietro");
        JButton btnCreaPaniere = new JButton("Crea paniere");

        btnCreaPaniere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PaniereDatiFrame();
            }
        });

        btnIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                UtenteFrame utfr = (UtenteFrame) SessionManager.getInstance().getSession().get("finestra_utente");
                utfr.setVisible(true);
            }
        });
        sud.add(btnIndietro);
        sud.add(btnCreaPaniere);
        c.add(nord, BorderLayout.NORTH);
        c.add(centro, BorderLayout.CENTER);
        c.add(sud, BorderLayout.SOUTH);


        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
