package it.progettojorick.view;



import it.progettojorick.business.CarrelloBusiness;
import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.RichiestaOrdineBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.*;
//import jdk.tools.jlink.internal.Jlink;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.html.HTML;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UTFDataFormatException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;



public class AcquistoFrame extends JFrame {

    private int x = 800;
    private int y = 700;

    Pagamento pag = (Pagamento)SessionManager.getInstance().getSession().get("pagamento_selezionato");
    PreferenzeConsegna pref = (PreferenzeConsegna) SessionManager.getInstance().getSession().get("preferenza_consegna");




    Utente u = (Utente) SessionManager.getInstance().getSession().get("utente");


    Carrello car = (Carrello) SessionManager.getInstance().getSession().get("carrello");




    public AcquistoFrame() {

        super("Finestra dell'Acquisto");

        AcquistoFrame _this = this;

        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - x / 2, (dim.height / 2) - y / 2);
        Border blackline;
        blackline = BorderFactory.createLineBorder(Color.black);

        c.setLayout(new BoxLayout(c,BoxLayout.PAGE_AXIS));
        c.setBackground(Color.white);

        JPanel nord = new JPanel();
        nord.setLayout(new FlowLayout());
        nord.setBackground(Color.white);

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro,BoxLayout.PAGE_AXIS));//GridLayout(6,1));
        centro.setBackground(Color.white);

        JPanel generalita = new JPanel();
        generalita.setLayout(new GridLayout(5,2));
        generalita.setBackground(Color.white);

        JPanel intropagamento = new JPanel();
        intropagamento.setLayout(new GridLayout(1,1));
        intropagamento.setBackground(Color.white);

        JPanel pagamento = new JPanel();
        pagamento.setLayout(new BoxLayout(pagamento,BoxLayout.PAGE_AXIS));//GridLayout(1,2));
        pagamento.setBackground(Color.white);

        JPanel bottonipagamento =new JPanel();
        bottonipagamento.setLayout(new FlowLayout());
        bottonipagamento.setBackground(Color.white);

        JPanel introconsegna = new JPanel();
        introconsegna.setLayout(new GridLayout(1,1));
        introconsegna.setBackground(Color.white);

        JPanel consegna = new JPanel();
        consegna.setLayout(new BoxLayout(consegna,BoxLayout.PAGE_AXIS));//GridLayout(1,2));
        consegna.setBackground(Color.white);

        JPanel bottoniconsegna = new JPanel();
        bottoniconsegna.setLayout(new FlowLayout());
        bottoniconsegna.setBackground(Color.white);

        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
        sud.setBackground(Color.white);

//        JPanel vuoto = new JPanel();
//        vuoto.setLayout(new FlowLayout());
//        vuoto.setSize(2,30);
//        vuoto.setVisible(true);

        JLabel intro = new JLabel("Conferma o modifica le tue generalità");
        intro.setFont(new Font("Serif", Font.BOLD, 22));
        nord.add(intro);

        JLabel lblNome = new JLabel("Nome: ");
        JLabel lblCognome = new JLabel("Cognome: ");
        JLabel lblEmail = new JLabel("Email: ");
        JLabel lblIndirizzo = new JLabel("Indirizzo di residenza: ");
        JLabel lblNumtelefono = new JLabel("Num. Telefono: ");
        JLabel numcartaU = new JLabel();
        JLabel circuitoU = new JLabel();
        JLabel codsicurezzaU = new JLabel();
        JLabel datascadenzaU = new JLabel();


        if(pag!=null){
         numcartaU = new JLabel(String.valueOf(pag.getNumCarta()));
         circuitoU = new JLabel(pag.getCircuito());
         codsicurezzaU = new JLabel(String.valueOf(pag.getCodSicurezza()));
         datascadenzaU = new JLabel(pag.getDataScadenza());}


        JLabel lblNomeU = new JLabel(u.getNome());
       // JTextField lblNomeU = new JTextField();
      //  lblNomeU.setText(u.getNome());
      //  lblNomeU.setEditable(false);
        JLabel lblCognomeU = new JLabel(u.getCognome());
        JLabel lblEmailU = new JLabel(u.getEmail());
        JLabel lblIndirizzoU = new JLabel(u.getIndirizzo());
        JLabel lblNumtelefonoU = new JLabel(u.getNumtelefono());

        TitledBorder Tnome;
        Tnome = BorderFactory.createTitledBorder(blackline,"Nome");
        lblNomeU.setBorder(Tnome);
        TitledBorder Tcognome;
        Tcognome = BorderFactory.createTitledBorder(blackline,"Cognome");
        lblCognomeU.setBorder(Tcognome);
        TitledBorder Temail;
        Temail = BorderFactory.createTitledBorder(blackline,"Email");
        lblEmailU.setBorder(Temail);
        TitledBorder Tindirizzo;
        Tindirizzo = BorderFactory.createTitledBorder(blackline,"Indirizzo");
        lblIndirizzoU.setBorder(Tindirizzo);
        TitledBorder Tnumtelefono;
        Tnumtelefono = BorderFactory.createTitledBorder(blackline,"Numero di Telefono");
        lblNumtelefonoU.setBorder(Tnumtelefono);

        //generalita.add(lblNome);
        generalita.add(lblNomeU);
        //generalita.add(lblCognome);
        generalita.add(lblCognomeU);
        //generalita.add(lblEmail);
        generalita.add(lblEmailU);
        //generalita.add(lblIndirizzo);
        generalita.add(lblIndirizzoU);
        //generalita.add(lblNumtelefono);
        generalita.add(lblNumtelefonoU);


        JLabel modpagamento = new JLabel("Modalità di Pagamento: ");


        JPanel daticarta = new JPanel();
        daticarta.setLayout(new GridLayout(2,2));
        daticarta.setBackground(Color.white);
        JLabel numcarta = new JLabel("Numero Carta: ");
        JLabel circuito = new JLabel("Circuito: ");
        JLabel codsicurezza = new JLabel("CVV: ");
        JLabel datascadenza = new JLabel("Data Scadenza: ");

        TitledBorder Tnumcarta;
        Tnumcarta = BorderFactory.createTitledBorder(blackline,"Numero Carta");
        numcartaU.setBorder(Tnumcarta);
        TitledBorder Tcircuito;
        Tcircuito = BorderFactory.createTitledBorder(blackline,"Circuito");
        circuitoU.setBorder(Tcircuito);
        TitledBorder Tcodsicurezza;
        Tcodsicurezza = BorderFactory.createTitledBorder(blackline,"CVV");
        codsicurezzaU.setBorder(Tcodsicurezza);
        TitledBorder Tdatascadenza;
        Tdatascadenza = BorderFactory.createTitledBorder(blackline,"Data Scadenza");
        datascadenzaU.setBorder(Tdatascadenza);


        //daticarta.add(numcarta);
        daticarta.add(numcartaU);
        //daticarta.add(circuito);
        daticarta.add(circuitoU);
        //daticarta.add(codsicurezza);
        daticarta.add(codsicurezzaU);
        //daticarta.add(datascadenza);
        daticarta.add(datascadenzaU);

        modpagamento.setFont(new Font("Serif", Font.BOLD, 20));
        intropagamento.add(modpagamento);
        pagamento.add(daticarta);


        //JButton confermapagamento = new JButton("Conferma");
        JButton modificapagamento = new JButton("Modifica");
        modificapagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.setVisible(false);
                new PagamentiSalvatiFrame();
            }
        });

       // bottonipagamento.add(confermapagamento);
        bottonipagamento.add(modificapagamento);

        JLabel preferenzeconsegna = new JLabel("Preferenze di consegna: ");
        JPanel daticonsegna = new JPanel();
        daticonsegna.setLayout(new GridLayout(2,2));
        daticonsegna.setBackground(Color.white);
        JLabel lblNominativo = new JLabel("Nominativo");
        JLabel lblNominativoU = new JLabel(u.getNome()+" "+u.getCognome());
        JLabel lblIndirizzoSped = new JLabel("Indirizzo di spedizione: ");
        JLabel lblIndirizzoSpedU = new JLabel(u.getIndirizzo());
        if(pref!=null){
            lblNominativoU=new JLabel(pref.getNominativo());
            lblIndirizzoSpedU=new JLabel((pref.getIndirizzoConsegna()));
        }

        TitledBorder Tnominativo;
        Tnominativo = BorderFactory.createTitledBorder(blackline,"Nominativo");
        lblNominativoU.setBorder(Tnominativo);
        TitledBorder Tindirizzosped;
        Tindirizzosped = BorderFactory.createTitledBorder(blackline,"Indirizzo di Spedizione");
        lblIndirizzoSpedU.setBorder(Tindirizzosped);

        //daticonsegna.add(lblNominativo);
        daticonsegna.add(lblNominativoU);
        //daticonsegna.add(lblIndirizzoSped);
        daticonsegna.add(lblIndirizzoSpedU);

        preferenzeconsegna.setFont(new Font("Serif", Font.BOLD, 20));
        introconsegna.add(preferenzeconsegna);
        consegna.add(daticonsegna);

       // JButton confermapreferenze = new JButton("Conferma");
        JButton modificapreferenze = new JButton("Modifica");

        modificapreferenze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               // SessionManager.getInstance().getSession().put("finestra_preferenze",new PreferenzeConsegnaFrame());
                new PreferenzeConsegnaDatiFrame();


            }
        });

      //  bottoniconsegna.add(confermapreferenze);
        bottoniconsegna.add(modificapreferenze);

        JPanel prodottidaacquistare = new JPanel();
        prodottidaacquistare.setBackground(Color.white);

        ArrayList<Prodotto> prodotti = car.getProdottiContenuti();
        prodottidaacquistare.setLayout(new GridLayout(prodotti.size()+1,3));
        JLabel nome = new JLabel("Nome prodotto:");
        nome.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel prezzo = new JLabel("Prezzo:");
        prezzo.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel quantitalbl = new JLabel("Quantità:");
        quantitalbl.setFont(new Font("Serif", Font.BOLD, 20));
        prodottidaacquistare.add(nome);
        prodottidaacquistare.add(prezzo);
        prodottidaacquistare.add(quantitalbl);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        float tot = 0;
        for(int i=0;i<prodotti.size();i++){


            int quantita = ProdottoBusiness.getInstance().getQuantita(car, prodotti.get(i));
            JLabel nomeprodotto = new JLabel(prodotti.get(i).getNome());

            float prezzoScont = prodotti.get(i).getPrezzo()-prodotti.get(i).getPrezzo()*prodotti.get(i).getSconto()/100;

            String prezzoScontato = df.format(prezzoScont);

            JLabel prezzoprodotto = new JLabel("€"+prezzoScontato);
            JLabel quantitaprodotto = new JLabel(String.valueOf(quantita));
            nomeprodotto.setFont(new Font("Serif", Font.PLAIN,18));
            prezzoprodotto.setFont(new Font("Serif", Font.PLAIN,18));
            quantitaprodotto.setFont(new Font("Serif", Font.PLAIN,18));
            tot += prezzoScont*quantita;
            prodottidaacquistare.add(nomeprodotto);
            prodottidaacquistare.add(prezzoprodotto);
            prodottidaacquistare.add(quantitaprodotto);
        }

        JButton indietro = new JButton("Indietro");
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                UtenteFrame utfr=(UtenteFrame) SessionManager.getInstance().getSession().get("finestra_utente");
                utfr.setVisible(true);

            }
        });

        String totale = df.format(tot);
        JLabel intrototale = new JLabel("Totale da pagare: ");
        JLabel total = new JLabel("€"+totale);
        JButton procedi = new JButton("Procedi all'acquisto");

        procedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                if(pag!=null) {
                    _this.dispose();

                    ArrayList<Prodotto> prodotti = car.getProdottiContenuti();

                    Iterator i = prodotti.iterator();

                    while(i.hasNext()){

                        Prodotto p = (Prodotto) i.next();
                        int quantita = ProdottoBusiness.getInstance().getQuantita(car,p);
                        int disponibilita = p.getDisponibilita();
                        ProdottoBusiness.getInstance().setDisponibilita(p,disponibilita-quantita );

                    }

                    JOptionPane.showMessageDialog(null, "L'ordine è stato effettuato.");
                    RichiestaOrdineBusiness.getInstance().inviaRichiestaOrdine("effettuato", car.getIdcarrello(), u.getEmailUtente(), pag.getNumCarta());
                   // SessionManager.getInstance().getSession().put()
                    car.setUsato(true);
                    CarrelloBusiness.getInstance().inserisciCarrello(u.getEmailUtente());
                    Carrello carNuovo = CarrelloBusiness.getInstance().carrelloUtente(u);
                    SessionManager.getInstance().getSession().put("carrello",carNuovo);
                    UtenteFrame utfr = (UtenteFrame) SessionManager.getInstance().getSession().get("finestra_utente");
                    utfr.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Le modalità di pagamento non sono valide");
                }
            }
        });
        //totale.setFont(totale.getFont().deriveFont(Font.BOLD));
        intrototale.setFont(new Font("Serif", Font.PLAIN,18));
        total.setFont(new Font("Serif", Font.BOLD, 20));
        sud.add(indietro);
        sud.add(intrototale);
        sud.add(total);
        sud.add(procedi);

        c.add(nord);


        centro.add(generalita);
        centro.add(Box.createRigidArea(new Dimension(0,20)));
        centro.add(intropagamento);
        centro.add(pagamento);
        centro.add(bottonipagamento);
        centro.add(introconsegna);
        centro.add(consegna);
        centro.add(bottoniconsegna);
        centro.add(prodottidaacquistare);

        c.add(centro);


        c.add(sud);




        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x,y);
        setVisible(true);
    }
}