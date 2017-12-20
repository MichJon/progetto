package it.progettojorick.view;



import it.progettojorick.business.CarrelloBusiness;
import it.progettojorick.business.RichiestaOrdineBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

//import static jdk.nashorn.internal.objects.NativeSymbol.iterator;

public class AcquistoFrame extends JFrame {

    private int x = 1024;
    private int y = 700;

    Pagamento pag = (Pagamento)SessionManager.getInstance().getSession().get("pagamento_selezionato");
    PreferenzeConsegna pref = (PreferenzeConsegna) SessionManager.getInstance().getSession().get("preferenza_consegna");



//    public JLabel getNumcartaU() {
//        return numcartaU;
//    }
//
//    public void setNumcartaU(JLabel numcartaU) {
//        this.numcartaU = numcartaU;
//    }
//
//    public JLabel getCircuitoU() {
//        return circuitoU;
//    }
//
//    public void setCircuitoU(JLabel circuitoU) {
//        this.circuitoU = circuitoU;
//    }
//
//    public JLabel getCodsicurezzaU() {
//        return codsicurezzaU;
//    }
//
//    public void setCodsicurezzaU(JLabel codsicurezzaU) {
//        this.codsicurezzaU = codsicurezzaU;
//    }
//
//    public JLabel getDatascadenzaU() {
//        return datascadenzaU;
//    }
//
//    public void setDatascadenzaU(JLabel datascadenzaU) {
//        this.datascadenzaU = datascadenzaU;
//    }

    Utente u = (Utente) SessionManager.getInstance().getSession().get("utente");
   // Persona p = (Persona) SessionManager.getInstance().getSession().get("persona");

    Carrello car = (Carrello) SessionManager.getInstance().getSession().get("carrello");
    //Carrello car = CarrelloBusiness.getInstance().carrelloUtente(u);



    public AcquistoFrame() {

        super("Finestra dell'Acquisto");

        AcquistoFrame _this = this;

        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - x / 2, (dim.height / 2) - y / 2);

        c.setLayout(new BoxLayout(c,BoxLayout.PAGE_AXIS));


        JPanel nord = new JPanel();
        nord.setLayout(new FlowLayout());

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro,BoxLayout.PAGE_AXIS));//GridLayout(6,1));

        JPanel generalita = new JPanel();
        generalita.setLayout(new GridLayout(5,2));

        JPanel pagamento = new JPanel();
        pagamento.setLayout(new BoxLayout(pagamento,BoxLayout.PAGE_AXIS));//GridLayout(1,2));

        JPanel bottonipagamento =new JPanel();
        bottonipagamento.setLayout(new FlowLayout());

        JPanel consegna = new JPanel();
        consegna.setLayout(new BoxLayout(consegna,BoxLayout.PAGE_AXIS));//GridLayout(1,2));

        JPanel bottoniconsegna = new JPanel();
        bottoniconsegna.setLayout(new FlowLayout());

        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());

//        JPanel vuoto = new JPanel();
//        vuoto.setLayout(new FlowLayout());
//        vuoto.setSize(2,30);
//        vuoto.setVisible(true);

        JLabel intro = new JLabel("Conferma o modifica le tue generalità");
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

        generalita.add(lblNome);
        generalita.add(lblNomeU);
        generalita.add(lblCognome);
        generalita.add(lblCognomeU);
        generalita.add(lblEmail);
        generalita.add(lblEmailU);
        generalita.add(lblIndirizzo);
        generalita.add(lblIndirizzoU);
        generalita.add(lblNumtelefono);
        generalita.add(lblNumtelefonoU);


        JLabel modpagamento = new JLabel("Modalità di Pagamento: ");


        JPanel daticarta = new JPanel();
        daticarta.setLayout(new GridLayout(2,4));
        JLabel numcarta = new JLabel("Numero Carta: ");
        JLabel circuito = new JLabel("Circuito: ");
        JLabel codsicurezza = new JLabel("CVV: ");
        JLabel datascadenza = new JLabel("Data Scadenza: ");


        daticarta.add(numcarta);
        daticarta.add(numcartaU);
        daticarta.add(circuito);
        daticarta.add(circuitoU);
        daticarta.add(codsicurezza);
        daticarta.add(codsicurezzaU);
        daticarta.add(datascadenza);
        daticarta.add(datascadenzaU);


        pagamento.add(modpagamento);
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
        JLabel lblNominativo = new JLabel("Nominativo");
        JLabel lblNominativoU = new JLabel(u.getNome()+" "+u.getCognome());
        JLabel lblIndirizzoSped = new JLabel("Indirizzo di spedizione: ");
        JLabel lblIndirizzoSpedU = new JLabel(u.getIndirizzo());
        if(pref!=null){
            lblNominativoU=new JLabel(pref.getNominativo());
            lblIndirizzoSpedU=new JLabel((pref.getIndirizzoConsegna()));
        }

        daticonsegna.add(lblNominativo);
        daticonsegna.add(lblNominativoU);
        daticonsegna.add(lblIndirizzoSped);
        daticonsegna.add(lblIndirizzoSpedU);

        consegna.add(preferenzeconsegna);
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

        ArrayList<Prodotto> prodotti = car.getProdottiContenuti();
        prodottidaacquistare.setLayout(new GridLayout(prodotti.size(),2));

        float tot = 0;
        for(int i=0;i<prodotti.size();i++){


            JLabel nomeprodotto = new JLabel(prodotti.get(i).getNome());
            JLabel prezzoprodotto = new JLabel(Float.toString(prodotti.get(i).getPrezzo()));
            tot += prodotti.get(i).getPrezzo();
            prodottidaacquistare.add(nomeprodotto);
            prodottidaacquistare.add(prezzoprodotto);
        }


        JLabel totale = new JLabel("Totale da pagare: "+tot+"€");
        JButton procedi = new JButton("Procedi all'acquisto");

        procedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                if(pag!=null) {
                    _this.dispose();
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

        sud.add(totale);
        sud.add(procedi);

        c.add(nord);


        centro.add(generalita);
        centro.add(Box.createRigidArea(new Dimension(0,20)));
        centro.add(pagamento);
        centro.add(bottonipagamento);
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