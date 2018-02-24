package it.progettojorick.view;

import it.progettojorick.business.CarrelloBusiness;
import it.progettojorick.business.PagamentoBusiness;
import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.dao.mysql.CarrelloDAO;
import it.progettojorick.model.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class ProdottiOrdineFrame extends JFrame {

    private int x = 350;
    private int y = 600;

    private Carrello c;


    public ProdottiOrdineFrame(Carrello c, long numcarta, Persona pers) {

        super("Finestra di Info");
        this.c=c;

        Border blackline;
        blackline = BorderFactory.createLineBorder(Color.black);

        Container cont = getContentPane();
        cont.setBackground(Color.white);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - x / 2, (dim.height / 2) - y / 2);

        cont.setLayout(new BorderLayout());
        // RegistrazioneListener listener = new RegistrazioneListener(this);

        ProdottiOrdineFrame _this = this;

        JPanel introgeneralita = new JPanel(new GridLayout(1,1));
        introgeneralita.setBackground(Color.white);
        JLabel lblGeneralita = new JLabel("Generalità dell'Utente:");
        lblGeneralita.setFont(new Font("Serif", Font.BOLD, 18));
        introgeneralita.add(lblGeneralita);
        JPanel generalita = new JPanel(new GridLayout(2,2));
        generalita.setBackground(Color.white);
        JPanel nord = new JPanel(new GridLayout(3,1));
        nord.setBackground(Color.white);
        JPanel centro = new JPanel(new GridLayout(4,1));
        centro.setBackground(Color.white);

     //   centro.setAlignmentY(CENTER_ALIGNMENT);
       // centro.setAlignmentX(getWidth()/2);
        //centro.setLayout(new BoxLayout(centro, BoxLayout.PAGE_AXIS));
        //.add(centro, new GridBagConstraints());
        JPanel sud = new JPanel(new FlowLayout());
        sud.setBackground(Color.white);

        JLabel nomeU = new JLabel(pers.getNome());
        JLabel congomeU = new JLabel(pers.getCognome());
        JLabel indirizzoU = new JLabel(pers.getIndirizzo());
        JLabel blank = new JLabel();

        TitledBorder TnomeU;
        TnomeU = BorderFactory.createTitledBorder(blackline,"Nome");
        nomeU.setBorder(TnomeU);
        TitledBorder TcognomeU;
        TcognomeU = BorderFactory.createTitledBorder(blackline,"Cognome");
        congomeU.setBorder(TcognomeU);
        TitledBorder TindirizzoU;
        TindirizzoU = BorderFactory.createTitledBorder(blackline,"Indirizzo");
        indirizzoU.setBorder(TindirizzoU);

        generalita.add(nomeU);
        generalita.add(congomeU);
        generalita.add(indirizzoU);
        generalita.add(blank);

        JLabel lblProdotti = new JLabel("Prodotti ordinati:");
        JLabel lblPagamento = new JLabel("Metodo di pagamento utilizzato:");
        lblPagamento.setFont(new Font("Serif", Font.BOLD, 18));
        lblProdotti.setFont(new Font("Serif", Font.BOLD, 18));

        JPanel prodottiPan = new JPanel();
        prodottiPan.setLayout(new BoxLayout(prodottiPan, BoxLayout.PAGE_AXIS));
        prodottiPan.setBackground(Color.white);

        ArrayList<Prodotto> prodotti= CarrelloBusiness.getInstance().prodottiContenuti(c);

        Iterator i = prodotti.iterator();
        while (i.hasNext()){

            Prodotto p = (Prodotto)i.next();

            JLabel lbl = new JLabel(p.getNome()+"(quantita:"+ ProdottoBusiness.getInstance().getQuantita(c,p)+")",JLabel.CENTER);
            lbl.setAlignmentX(JLabel.CENTER_ALIGNMENT);

            prodottiPan.add(lbl);



        }

        Pagamento Pag = PagamentoBusiness.getInstance().trovaPagamento(numcarta);
        JPanel daticarta = new JPanel(new GridLayout(2,2));
        daticarta.setBackground(Color.white);
        JLabel numerocarta = new JLabel(String.valueOf(Pag.getNumCarta()));
        JLabel circuito = new JLabel(Pag.getCircuito());
        JLabel codsicurezza = new JLabel(String.valueOf(Pag.getCodSicurezza()));
        JLabel datascadenza = new JLabel(Pag.getDataScadenza());

        TitledBorder Tnumcarta;
        Tnumcarta = BorderFactory.createTitledBorder(blackline,"Numero Carta");
        numerocarta.setBorder(Tnumcarta);
        TitledBorder Tcircuito;
        Tcircuito = BorderFactory.createTitledBorder(blackline,"Circuito");
        circuito.setBorder(Tcircuito);
        TitledBorder Tcodsicurezza;
        Tcodsicurezza = BorderFactory.createTitledBorder(blackline,"CVV");
        codsicurezza.setBorder(Tcodsicurezza);
        TitledBorder Tdatascadenza;
        Tdatascadenza = BorderFactory.createTitledBorder(blackline,"Data Scadenza");
        datascadenza.setBorder(Tdatascadenza);


        //daticarta.add(numcarta);
        daticarta.add(numerocarta);
        //daticarta.add(circuito);
        daticarta.add(circuito);
        //daticarta.add(codsicurezza);
        daticarta.add(codsicurezza);
        //daticarta.add(datascadenza);
        daticarta.add(datascadenza);

        centro.add(lblProdotti);
        centro.add(prodottiPan);
        centro.add(lblPagamento);
        centro.add(daticarta);

        JButton indietro = new JButton("Indietro");
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
            }
        });

       sud.add(indietro);

       nord.add(introgeneralita);
       nord.add(generalita);
        nord.add(lblProdotti);

       cont.add(nord, BorderLayout.NORTH);
        cont.add(centro, BorderLayout.CENTER);             // pattern command
        cont.add(sud, BorderLayout.SOUTH);

        setSize(x, y);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
