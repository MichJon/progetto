package it.progettojorick.view;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import it.progettojorick.business.CarrelloBusiness;
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

    private int x = 400;
    private int y = 500;


    public InfoProdottoFrame(Prodotto p) {



        super("Finestra di Info");

         this.p = p;// = //(Prodotto)SessionManager.getInstance().getSession().get("prodotto_aperto");

        Container RegPane = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        RegPane.setLayout(new BorderLayout());
       // RegistrazioneListener listener = new RegistrazioneListener(this);

        InfoProdottoFrame _this = this;

        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(7,2));
        JPanel sud = new JPanel(new FlowLayout());

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblDescrizione = new JLabel("Descrizione:");
        JLabel lblPrezzo = new JLabel("Prezzo:");
        JLabel lblCategoria = new JLabel("Categoria:");
        JLabel lblDescrizioneProduttore = new JLabel("Descrizione Produttore:");
        JLabel lblDescrizioneDistributore = new JLabel("Descrizione Distributore:");

        JLabel lblNomeI = new JLabel(p.getNome());
        JLabel lblDescrizioneI = new JLabel(p.getDescrizione());
        JLabel lblPrezzoI = new JLabel(Float.toString(p.getPrezzo()));
        JLabel lblCategoriaI = new JLabel(p.getCategoria().getNomecategoria());
        JLabel lblDescrizioneProduttoreI = new JLabel(p.getProduttore().getDescrizione());
        JLabel lblDescrizioneDistributoreI = new JLabel(p.getDistributore().getDescrizione());

        JPanel prodottiCont = new JPanel();
        prodottiCont.setLayout(new BoxLayout(prodottiCont, BoxLayout.PAGE_AXIS));



        centro.add(lblNome);
        centro.add(lblNomeI);
        centro.add(lblDescrizione);
        centro.add(lblDescrizioneI);
        centro.add(lblPrezzo);
        centro.add(lblPrezzoI);
        centro.add(lblCategoria);
        centro.add(lblCategoriaI);
        centro.add(lblDescrizioneProduttore);
        centro.add(lblDescrizioneProduttoreI);
        centro.add(lblDescrizioneDistributore);
        centro.add(lblDescrizioneDistributoreI);

        if (p.getProdottiContenuti()!=null){
            centro.add(new JLabel("Prodotti contenuti"));

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
        sud.add(indietro);

        JButton aggCarrello= new JButton("Aggiungi al carrello");
        aggCarrello.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utente u = (Utente)SessionManager.getInstance().getSession().get("utente");
                //Carrello c = CarrelloBusiness.getInstance().carrelloUtente(u);

                Carrello c = (Carrello) SessionManager.getInstance().getSession().get("carrello");

                    if(!CarrelloBusiness.getInstance().isPresente(p,c)){
                        CarrelloBusiness.getInstance().inserisciProdottoNelCarrello(p, c);
                        p.setDalPaniere(false);
                    JOptionPane.showMessageDialog(null,"Il prodotto è stato inserito nel carrello.");
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Il prodotto è già presente nel carrello.");




                _this.dispose();
//                UtenteFrame ufr=(UtenteFrame)SessionManager.getInstance().getSession().get("finestra_utente");
//                ufr.setVisible(true);
            }
        });
        sud.add(aggCarrello);


        JPanel immagine = new JPanel(new BorderLayout());
        ImageIcon img = new ImageIcon("./images/"+p.getImgUrl());
        Image image = img.getImage().getScaledInstance(200,200,0);
        ImageIcon newImg = new ImageIcon(image);
        immagine.add(new JLabel(newImg), BorderLayout.CENTER);
        pack();

        RegPane.add(immagine,BorderLayout.NORTH);
        RegPane.add(centro, BorderLayout.CENTER);             // pattern command
        RegPane.add(sud, BorderLayout.SOUTH);

        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
    }
}
