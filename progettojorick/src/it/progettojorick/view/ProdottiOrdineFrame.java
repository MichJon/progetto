package it.progettojorick.view;

import it.progettojorick.business.CarrelloBusiness;
import it.progettojorick.dao.mysql.CarrelloDAO;
import it.progettojorick.model.Carrello;
import it.progettojorick.model.Prodotto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class ProdottiOrdineFrame extends JFrame {

    private int x = 200;
    private int y = 250;

    private Carrello c;


    public ProdottiOrdineFrame(Carrello c) {

        super("Finestra di Info");
        this.c=c;

        Container RegPane = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - x / 2, (dim.height / 2) - y / 2);

        RegPane.setLayout(new BorderLayout());
        // RegistrazioneListener listener = new RegistrazioneListener(this);

        ProdottiOrdineFrame _this = this;

        JPanel nord = new JPanel(new FlowLayout());
        JPanel centro = new JPanel();

     //   centro.setAlignmentY(CENTER_ALIGNMENT);
       // centro.setAlignmentX(getWidth()/2);
        centro.setLayout(new BoxLayout(centro, BoxLayout.PAGE_AXIS));
        //.add(centro, new GridBagConstraints());
        JPanel sud = new JPanel(new FlowLayout());

        JLabel lblProdotti = new JLabel("Prodotti ordinati:");
        nord.add(lblProdotti);

        ArrayList<Prodotto> prodotti= CarrelloBusiness.getInstance().prodottiContenuti(c);

        Iterator i = prodotti.iterator();
        while (i.hasNext()){

            Prodotto p = (Prodotto)i.next();

            JLabel lbl = new JLabel(p.getNome(),JLabel.CENTER);
            lbl.setAlignmentX(JLabel.CENTER_ALIGNMENT);

            centro.add(lbl);



        }

        JButton indietro = new JButton("Indietro");
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
            }
        });

       sud.add(indietro);

       RegPane.add(nord, BorderLayout.NORTH);
        RegPane.add(centro, BorderLayout.CENTER);             // pattern command
        RegPane.add(sud, BorderLayout.SOUTH);

        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
