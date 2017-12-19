//package it.progettojorick.view;
//
//import it.progettojorick.business.PreferenzeConsegnaBusiness;
//import it.progettojorick.business.SessionManager;
//import it.progettojorick.model.PreferenzeConsegna;
//import it.progettojorick.model.Utente;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//public class PreferenzeConsegnaFrame extends JFrame {
//
//    int x=1024;
//    int y=700;
//
//    Utente u = (Utente) SessionManager.getInstance().getSession().get("utente");
//
//    public PreferenzeConsegnaFrame(){
//
//        super("Preferenze salvate");
//
//        PreferenzeConsegnaFrame _this = this;
//
//        getContentPane().setLayout(new BorderLayout());
//
//        PreferenzeConsegna pref = PreferenzeConsegnaBusiness.getInstance().preferenzaConsegna(u);
//        JPanel sud = new JPanel(new FlowLayout());
//
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);
//
//        //ArrayList<Pagame> prodotti = CarrelloBusiness.getInstance().prodottiContenuti(c);
//
//
//        PreferenzeConsegnaTableModel pctm = new PreferenzeConsegnaTableModel(pref);
//
//        JTable pagamenti = new JTable(pctm);
//        JButton aggiungiPreferenza = new JButton("Aggiungi");
//        aggiungiPreferenza.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                new PreferenzeConsegnaDatiFrame();
//
//
//            }
//        });
//
//        getContentPane().add(new JScrollPane(pagamenti), BorderLayout.CENTER);
//        sud.add(aggiungiPreferenza);
//        //sud.add(seleziona);
//        getContentPane().add(sud, BorderLayout.SOUTH);
//
//
//        setSize(x, y);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//}
//    }
