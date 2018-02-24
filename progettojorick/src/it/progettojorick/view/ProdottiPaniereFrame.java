package it.progettojorick.view;

import it.progettojorick.business.*;
import it.progettojorick.model.*;
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

    ArrayList<Prodotto> prodottiList= PaniereBusiness.getInstance().prodottiContenuti(p);

    Utente u =(Utente) SessionManager.getInstance().getSession().get("utente");

    Carrello car=(Carrello) SessionManager.getInstance().getSession().get("carrello");

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
        c.setBackground(Color.white);
        nord.setBackground(Color.white);
        centro.setBackground(Color.white);
        sud.setBackground(Color.white);


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
        aggPanAlCarrello.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                ArrayList<Prodotto> prodContenuti = p.getProdotti();
        if(prodContenuti!=null){

                Iterator i = prodContenuti.iterator();

                while(i.hasNext()){

                    Prodotto prod =(Prodotto) i.next();

                    prod.setDalPaniere(true);
                    if(!CarrelloBusiness.getInstance().isPresente(prod,car)&&prod.getDisponibilita()>0) {
                        CarrelloBusiness.getInstance().inserisciProdottoNelCarrello(prod, car);

                    }
                    else if (prod.getDisponibilita()>0){
                        //JOptionPane.showMessageDialog(null,"Il prodotto "+prod.getNome()+" è già presente nel carrello.");

                        Object[] options = {"Si",
                                "No "};
                        int n = JOptionPane.showOptionDialog(null,
                                "Il prodotto "+prod.getNome()+" è già presente nel carrello. Vuoi aumentarne la quantità?",
                                "Attenzione",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,     //do not use a custom Icon
                                options,  //the titles of buttons
                                options[1]); //default button title
                        if(n==JOptionPane.YES_OPTION){
                            int quantita =ProdottoBusiness.getInstance().getQuantita(car,prod);
                           // String newQuantita=String.valueOf(quantita+1);

                            if (prod.getDisponibilita()>quantita) {
                                ProdottoBusiness.getInstance().setQuantita(quantita + 1, car, prod);

                                JOptionPane.showMessageDialog(null, "Quantità aumentata.");
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Impossibile aumentare la quantità. Corrisponde già alla quantità massima presente.");
                            }

                        }

                    }
                    else if (prod.getDisponibilita()==0){
                        JOptionPane.showMessageDialog(null,"Impossibile inserire nel carrello, prodotto " + prod.getNome()+" terminato.");
                    }

                }
                JOptionPane.showMessageDialog(null, " Operazione conclusa. ");//I prodotti del paniere sono stati aggiunti al carrello.");

        }else
            JOptionPane.showMessageDialog(null, "Non sono presenti prodotti, impossibile inserire nel carrello.");
            }
        });
        sud.add(modificaPaniere);
        modificaPaniere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                _this.dispose();
                new ModificaProdottiPaniereFrame();
              //  new ProdottiDisponibiliListaFrame();
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
                SessionManager.getInstance().getSession().put("paniere",null);
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

        JButton elimina = new JButton("Elimina questo paniere");
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                _this.dispose();
                JOptionPane.showMessageDialog(null, "Il paniere "+p.getNome()+" è stato eliminato.");
                PaniereBusiness.getInstance().eliminaPaniere(p);
                SessionManager.getInstance().getSession().put("paniere",null);

                ElencoPanieriView epw  = new ElencoPanieriView();
                SessionManager.getInstance().getSession().put("finestra_elenco_panieri",epw);


            }
        });

        sud.add(elimina);


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
