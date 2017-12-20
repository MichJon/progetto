package it.progettojorick.view;

import it.progettojorick.business.CategoriaBusiness;
import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Categoria;
import it.progettojorick.model.Paniere;
import it.progettojorick.model.Prodotto;
import it.progettojorick.model.Utente;
import utilities.BottoneConImg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

public class ProdottiPaniereFrame extends JFrame {

    private int x=1024;
    private int y=700;

    Paniere p = (Paniere)SessionManager.getInstance().getSession().get("paniere");

    ArrayList<Prodotto> prodottiList= ProdottoBusiness.getInstance().prodottiPresenti();

    Utente u =(Utente) SessionManager.getInstance().getSession().get("utente");
//
//    public void setProdottiList(ArrayList<Prodotto> prodottiList) {
//        this.prodottiList = prodottiList;
//    }

    public ProdottiPaniereFrame(ArrayList<Prodotto> prodottiList){



        super("Prodotti nel paniere");

        this.prodottiList = prodottiList;
        ProdottiPaniereFrame _this=this;
        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        c.setLayout(new BorderLayout());
        JPanel nord = new JPanel(new FlowLayout());
        JPanel centro = new JPanel(new FlowLayout());
        JPanel sud = new JPanel();
        c.add(nord,BorderLayout.NORTH);
        c.add(centro,BorderLayout.CENTER);
        c.add(sud, BorderLayout.SOUTH);

        JButton panieri = new JButton("Panieri");
        panieri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.setVisible(false);
                new ElencoPanieriView();
            }
        });
        JButton carrello = new JButton("Carrello");
        JButton aggPanAlCarrello = new JButton("Aggiungi paniere al carrello");
        JButton modificaPaniere = new JButton("Modifica paniere");
        JLabel nomePaniere = new JLabel(p.getNome());

        nord.add(nomePaniere);

        sud.add(panieri);
        sud.add(carrello);
        sud.add(aggPanAlCarrello);
        sud.add(modificaPaniere);
        modificaPaniere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                _this.dispose();
                new ModificaProdottiPaniereFrame();
            }
        });
        carrello.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.setVisible(false);
                try{
                SessionManager.getInstance().getSession().put("finestra_carrello",new CarrelloFrame());
                }catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(null,"Non sono presenti prodotti nel carrello");
                    _this.setVisible(true);
                }
            }
        });
        JMenuBar bar = new JMenuBar();
        JMenu mostraTutti = new JMenu("Torna al catalogo");
       // JMenu categorie = new JMenu("Categorie");
        bar.add(mostraTutti);

       // bar.add(categorie);
        mostraTutti.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                _this.setVisible(false);
               UtenteFrame fr = new UtenteFrame(ProdottoBusiness.getInstance().prodottiPresenti());

                    SessionManager.getInstance().getSession().put("finestra_utente",fr);

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }


        });



        try{
            mostraProdotti(centro);
        } catch (NullPointerException e){
            centro.add(new JLabel("Non sono presenti prodotti in questa categoria."));
        }


        setJMenuBar(bar);

        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void mostraProdotti(JPanel p){

        Iterator j = prodottiList.iterator();

        while(j.hasNext()){

            Prodotto prod = (Prodotto)j.next();

            SessionManager.getInstance().getSession().put("prodotto_aperto",prod);
            BottoneConImg b = new BottoneConImg(prod.getImgUrl(),prod.getNome(),Float.toString(prod.getPrezzo()));

            p.add(b);


        }

    }
}
