package it.progettojorick.view;

import it.progettojorick.business.CarrelloBusiness;
import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Carrello;
import it.progettojorick.model.Prodotto;
import it.progettojorick.model.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarrelloFrame extends JFrame {

    int x=1024;
    int y=700;

    Utente u = (Utente)SessionManager.getInstance().getSession().get("utente");

    public CarrelloFrame(){

        super("Carrello");

        CarrelloFrame _this = this;

        getContentPane().setLayout(new BorderLayout());

        //Carrello c = CarrelloBusiness.getInstance().carrelloUtente(u);
        Carrello c = (Carrello)SessionManager.getInstance().getSession().get("carrello");
        JPanel sud = new JPanel(new FlowLayout());

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        ArrayList<Prodotto> prodotti = CarrelloBusiness.getInstance().prodottiContenuti(c);


        CarrelloTableModel ctm = new CarrelloTableModel(prodotti);

        JTable carrello = new JTable(ctm);
        JButton procedi = new JButton("Procedi all'acquisto");
        if (c.getProdottiContenuti().size()==0)
            procedi.setEnabled(false);

        procedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                _this.setVisible(false);
                AcquistoFrame acfr = new AcquistoFrame();
                SessionManager.getInstance().getSession().put("finestra_acquisto",acfr);


            }
        });

        getContentPane().add(new JScrollPane(carrello), BorderLayout.CENTER);
        sud.add(procedi);
        getContentPane().add(sud, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x,y);
        setVisible(true);
    }
}
