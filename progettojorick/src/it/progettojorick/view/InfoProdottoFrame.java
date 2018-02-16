package it.progettojorick.view;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import it.progettojorick.business.CarrelloBusiness;
import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Carrello;
import it.progettojorick.model.Prodotto;
import it.progettojorick.model.Utente;
//import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class InfoProdottoFrame extends JFrame {

    Prodotto p;

    private int x = 600;
    private int y = 450;


    public InfoProdottoFrame(Prodotto p) {




        super("Finestra di Info");

         this.p = p;// = //(Prodotto)SessionManager.getInstance().getSession().get("prodotto_aperto");

        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        c.setLayout(new BorderLayout());
       // RegistrazioneListener listener = new RegistrazioneListener(this);

        InfoProdottoFrame _this = this;

        JPanel generale = new JPanel(new GridLayout(1,2));
        JPanel details = new JPanel(new BorderLayout());
        JPanel centro = new JPanel();
        if(p.getProdottiContenuti()!=null)
        centro.setLayout(new GridLayout(7,2));
        else
            centro.setLayout(new GridLayout(6,2));
        JPanel sud = new JPanel(new GridLayout(1,2));

        JLabel lblDescrizione = new JLabel("Descrizione:");
        JLabel lblPrezzo = new JLabel("Prezzo:");
        JLabel lblCategoria = new JLabel("Categoria:");
        JLabel lblDescrizioneProduttore = new JLabel("Info Produttore:");
        JLabel lblDescrizioneDistributore = new JLabel("Info Distributore:");

        JLabel lblNomeI = new JLabel(p.getNome());
        lblNomeI.setFont(new Font("Serif", Font.PLAIN, 22));
        JLabel lblDescrizioneI = new JLabel(p.getDescrizione());
        JLabel lblPrezzoI = new JLabel("€"+Float.toString(p.getPrezzo()));
        lblPrezzoI.setFont(new Font("Serif", Font.PLAIN, 18));
        JLabel lblCategoriaI = new JLabel(p.getCategoria().getNomecategoria());
        JLabel lblDescrizioneProduttoreI = new JLabel(p.getProduttore().getDescrizione());
        JLabel lblDescrizioneDistributoreI = new JLabel(p.getDistributore().getDescrizione());
        JLabel lblblank = new JLabel("");

        JPanel prodottiCont = new JPanel();
        prodottiCont.setBackground(Color.white);
        prodottiCont.setLayout(new BoxLayout(prodottiCont, BoxLayout.PAGE_AXIS));

        JPanel immagine = new JPanel(new BorderLayout());
        ImageIcon img = new ImageIcon("./images/"+p.getImgUrl());
        Image image = img.getImage().getScaledInstance(200,200,0);
        ImageIcon newImg = new ImageIcon(image);
        immagine.add(new JLabel(newImg), BorderLayout.CENTER);
        pack();

        centro.add(lblNomeI);
        centro.add(lblblank);  //SPAZIO VUOTO
        centro.add(lblPrezzo);
        centro.add(lblPrezzoI);
        centro.add(lblDescrizione);
        centro.add(lblDescrizioneI);
        centro.add(lblCategoria);
        centro.add(lblCategoriaI);
        centro.add(lblDescrizioneProduttore);
        centro.add(lblDescrizioneProduttoreI);
        centro.add(lblDescrizioneDistributore);
        centro.add(lblDescrizioneDistributoreI);

        if (p.getProdottiContenuti()!=null){
            JLabel prodcont = new JLabel("Prodotti Contenuti");
            prodcont.setBackground(Color.white);
            centro.add(prodcont);

            Iterator i = p.getProdottiContenuti().iterator();

            while(i.hasNext()){
                Prodotto pr =(Prodotto) i.next();

                prodottiCont.add(new JLabel(pr.getNome()));


            }
            centro.add(prodottiCont);

        }

        JButton indietro = new JButton("Indietro");
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
//                UtenteFrame u=(UtenteFrame)SessionManager.getInstance().getSession().get("finestra_utente");
//                u.setVisible(true);
            }
        });


        JButton aggCarrello= new JButton("Aggiungi al carrello");
        aggCarrello.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utente u = (Utente) SessionManager.getInstance().getSession().get("utente");
                //Carrello c = CarrelloBusiness.getInstance().carrelloUtente(u);

                if (u != null) {

                    Carrello c = (Carrello) SessionManager.getInstance().getSession().get("carrello");

                    if (!CarrelloBusiness.getInstance().isPresente(p, c) && p.getDisponibilita() > 0) {

                        CarrelloBusiness.getInstance().inserisciProdottoNelCarrello(p, c);
                        p.setDalPaniere(false);
                        JOptionPane.showMessageDialog(null, "Il prodotto è stato inserito nel carrello.");
                    } else if (p.getDisponibilita() > 0) {
                        //   JOptionPane.showMessageDialog(null,"Il prodotto è già presente nel carrello.");

                        Object[] options = {"Si",
                                "No "};
                        int n = JOptionPane.showOptionDialog(null,
                                "Il prodotto è già presente nel carrello. Vuoi aumentarne la quantità?",
                                "Attenzione",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,     //do not use a custom Icon
                                options,  //the titles of buttons
                                options[1]); //default button title

                        if (n == JOptionPane.YES_OPTION) {

                            int quantita = ProdottoBusiness.getInstance().getQuantita(c, p);
                            // String newQuantita=String.valueOf(quantita+1);
                            if (p.getDisponibilita() > quantita) {
                                ProdottoBusiness.getInstance().setQuantita(quantita + 1, c, p);

                                JOptionPane.showMessageDialog(null, "Quantità aumentata.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Impossibile aumentare la quantità. Corrisponde già alla quantità massima presente.");
                            }
                        }

                    } else if (p.getDisponibilita() == 0) {
                        JOptionPane.showMessageDialog(null, "Impossibile inserire nel carrello, prodotto terminato.");
                    }


                    _this.dispose();
//                UtenteFrame ufr=(UtenteFrame)SessionManager.getInstance().getSession().get("finestra_utente");
//                ufr.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Effettua il login prima di continuare!");
//                    _this.setVisible(false);
//                    UtenteFrame utfr =(UtenteFrame) SessionManager.getInstance().getSession().get("finestra_utente");
//                    utfr.setVisible(false);
//                    new LoginFrame();
                }
            }
        });
        sud.add(indietro);
        sud.add(aggCarrello);

        immagine.setBackground(Color.white);
        centro.setBackground(Color.white);
        sud.setBackground(Color.white);

        details.add(centro, BorderLayout.CENTER);             // pattern command
        details.add(sud, BorderLayout.SOUTH);
        generale.add(immagine);
        generale.add(details);
        c.add(generale,BorderLayout.CENTER);

        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
    }
}
