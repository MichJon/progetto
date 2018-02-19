package it.progettojorick.view;

import it.progettojorick.business.CarrelloBusiness;
import it.progettojorick.business.PaniereBusiness;
import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Carrello;
import it.progettojorick.model.Prodotto;
import it.progettojorick.model.Utente;
import utilities.SpinnerEditor;
import utilities.SpinnerRenderer;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;

public class CarrelloFrame extends JFrame {

    int x=1024;
    int y=700;

    Utente u = (Utente)SessionManager.getInstance().getSession().get("utente");
   // Carrello c = (Carrello)SessionManager.getInstance().getSession().get("carrello");
   Carrello c = CarrelloBusiness.getInstance().carrelloUtente(u);
    public CarrelloFrame(){

        super("Carrello");

        controllaProdotti();

        CarrelloFrame _this = this;

        getContentPane().setLayout(new BorderLayout());


        SessionManager.getInstance().getSession().put("carrello",c);

        JPanel sud = new JPanel(new FlowLayout());

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        ArrayList<Prodotto> prodotti = CarrelloBusiness.getInstance().prodottiContenuti(c);


        CarrelloTableModel ctm = new CarrelloTableModel(prodotti);

        JTable carrello = new JTable(ctm);
//        TableColumn tc = carrello.getColumnModel().getColumn(2);
        //tc.setCellEditor(new SpinnerEditor());
//        tc.setCellRenderer(new SpinnerRenderer());
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

        JButton indietro = new JButton("Indietro");
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.setVisible(false);
                UtenteFrame utfr= (UtenteFrame)SessionManager.getInstance().getSession().get("finestra_utente");
                utfr.setVisible(true);
            }
        });

        JButton btnRimuovi = new JButton("Rimuovi prodotto dal carrello");


        btnRimuovi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //RIMOZIONE E NUOVA QUERY NEL DAO PER RIMUOVERE
                //REFRESH
                int index = carrello.getSelectedRow();

                if(index!=-1){
                   String nome = (String)carrello.getModel().getValueAt(index, 0);
                    Prodotto pr = ProdottoBusiness.getInstance().trovaProdotto(nome);
                    CarrelloBusiness.getInstance().rimuoviProdottoDalCarrello(pr,c);
                    _this.dispose();
                    new CarrelloFrame();
                }else JOptionPane.showMessageDialog(null, "Selezionare un prodotto da rimuovere.");

            }
        });


        getContentPane().add(new JScrollPane(carrello), BorderLayout.CENTER);
        sud.add(indietro);
        sud.add(btnRimuovi);
        sud.add(procedi);
        getContentPane().add(sud, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x,y);
        setVisible(true);
    }


    private void controllaProdotti(){

        ArrayList<Prodotto> prodotti = c.getProdottiContenuti();
        Iterator i = prodotti.iterator();
        while (i.hasNext()){

            Prodotto p =(Prodotto) i.next();
            int quantita = ProdottoBusiness.getInstance().getQuantita(c,p);

            if (p.getDisponibilita()<quantita){

                if(p.getDisponibilita()==0){
                    //RIMUOVERE DAL CARRELLO
                    CarrelloBusiness.getInstance().rimuoviProdottoDalCarrello(p,c);

                    JOptionPane.showMessageDialog(null,"Il prodotto "+p.getNome()+" è stato rimosso dal carrello perché è terminato.");

                }
                else {
                    ProdottoBusiness.getInstance().setQuantita(p.getDisponibilita(), c, p);
                    JOptionPane.showMessageDialog(null, "La quantità del prodotto " + p.getNome() + " è stata cambiata poichè la disponibilità è variata.");
                }
            }






        }

    }


}
