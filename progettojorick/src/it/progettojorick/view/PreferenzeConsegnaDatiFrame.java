package it.progettojorick.view;

import it.progettojorick.business.PreferenzeConsegnaBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.PreferenzeConsegna;
import it.progettojorick.model.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreferenzeConsegnaDatiFrame extends JFrame {

    private int x = 400;
    private int y = 250;

    JTextField nominativoU = new JTextField();
    JTextField indirizzoU = new JTextField();

    Utente u = (Utente)SessionManager.getInstance().getSession().get("utente");

    public PreferenzeConsegnaDatiFrame() {

        super("Finestra dati consegna");


        PreferenzeConsegnaDatiFrame _this = this;

        Container RegPane = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - x / 2, (dim.height / 2) - y / 2);

        RegPane.setLayout(new BorderLayout());

        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(2,2));
        JPanel sud = new JPanel(new FlowLayout());

        JLabel nominativo = new JLabel("Nominativo:");
        JLabel indirizzo = new JLabel("Indirizzo di consegna:");


        centro.add(nominativo);
        centro.add(nominativoU);
        centro.add(indirizzo);
        centro.add(indirizzoU);

        JButton conferma = new JButton("Conferma");
        conferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                PreferenzeConsegnaBusiness.getInstance().inserisciPreferenza(u,nominativoU.getText(),indirizzoU.getText());
                _this.dispose();
                //SessionManager.getInstance().getSession().put("finestra_preferenze",new PreferenzeConsegnaFrame());
                PreferenzeConsegna pr= PreferenzeConsegnaBusiness.getInstance().preferenzaConsegna(u);
                SessionManager.getInstance().getSession().put("preferenza_consegna",pr);
                AcquistoFrame ac= (AcquistoFrame)SessionManager.getInstance().getSession().get("finestra_acquisto");
                ac.dispose();
                SessionManager.getInstance().getSession().put("finestra_acquisto", new AcquistoFrame());




            }
        });

        JButton indietro = new JButton("Indietro");
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                AcquistoFrame acqfr=(AcquistoFrame)SessionManager.getInstance().getSession().get("finestra_acquisto");
                acqfr.setVisible(true);
            }
        });

        sud.add(indietro);
        sud.add(conferma);

        JPanel nord = new JPanel();
        nord.setLayout(new FlowLayout());
        nord.add(new JLabel("Inserisci i dati della consegna:"));

        RegPane.add(centro, BorderLayout.CENTER);             // pattern command
        RegPane.add(sud,BorderLayout.SOUTH);
        RegPane.add(nord,BorderLayout.NORTH);



        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



}
