package it.progettojorick.view;

import it.progettojorick.business.CarrelloBusiness;
import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Carrello;
import it.progettojorick.model.Prodotto;
import it.progettojorick.model.Utente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CarrelloFrame extends JFrame {

    int x=1024;
    int y=700;

    Utente u = (Utente)SessionManager.getInstance().getSession().get("utente");

    public CarrelloFrame(){

        super("Carrello");

        getContentPane().setLayout(new BorderLayout());

        Carrello c = CarrelloBusiness.getInstance().carrelloUtente(u);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        ArrayList<Prodotto> prodotti = CarrelloBusiness.getInstance().prodottiContenuti(c);


        CarrelloTableModel ctm = new CarrelloTableModel(prodotti);

        JTable carrello = new JTable(ctm);
        getContentPane().add(new JScrollPane(carrello), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x,y);
        setVisible(true);
    }
}
