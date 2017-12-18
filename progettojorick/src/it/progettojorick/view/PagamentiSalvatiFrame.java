package it.progettojorick.view;

import it.progettojorick.business.PagamentoBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Pagamento;
import it.progettojorick.model.Utente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;

//import static com.sun.glass.ui.Cursor.setVisible;

public class PagamentiSalvatiFrame extends JFrame{
    int x=1024;
    int y=700;

    Utente u = (Utente) SessionManager.getInstance().getSession().get("utente");

    public PagamentiSalvatiFrame(){

        super("Pagamenti salvati");

        PagamentiSalvatiFrame _this = this;

        getContentPane().setLayout(new BorderLayout());

        ArrayList<Pagamento> p = PagamentoBusiness.getInstance().pagamentiSalvati(u);
        JPanel sud = new JPanel(new FlowLayout());

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        //ArrayList<Pagame> prodotti = CarrelloBusiness.getInstance().prodottiContenuti(c);


        PagamentiSalvatiTableModel pstm = new PagamentiSalvatiTableModel(p);

        JTable pagamenti = new JTable(pstm);
        JButton aggiungiPagamento = new JButton("Aggiungi");

        aggiungiPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                _this.dispose();
                new PagamentoDatiFrame();


            }
        });

        JButton seleziona = new JButton("Seleziona");
        seleziona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.setVisible(false);
                //AcquistoFrame acq=(AcquistoFrame)SessionManager.getInstance().getSession().get("finestra_acquisto");

                int index = pagamenti.getSelectedRow();
                long numcarta = (long)pagamenti.getModel().getValueAt(index,0);
              //  String numCarta=pagamenti.getModel().getValueAt(index,0).toString();
//                String circuito = (String)pagamenti.getModel().getValueAt(index,1);
//                String cvv= pagamenti.getModel().getValueAt(index,2).toString();
//                String data= (String)pagamenti.getModel().getValueAt(index,3);

               Pagamento p = PagamentoBusiness.getInstance().trovaPagamento(numcarta);
               SessionManager.getInstance().getSession().put("pagamento_selezionato",p);
               SessionManager.getInstance().getSession().put("finestra_acquisto", new AcquistoFrame());


//
//                acq.setNumcartaU(new JLabel(numCarta));
//                acq.setCircuitoU(new JLabel(circuito));
//                acq.setCodsicurezzaU(new JLabel(cvv));
//                acq.setDatascadenzaU(new JLabel(data));
//                acq.setVisible(false);
//                acq.setVisible(true);

            }
        });

        getContentPane().add(new JScrollPane(pagamenti), BorderLayout.CENTER);
        sud.add(aggiungiPagamento);
        sud.add(seleziona);
        getContentPane().add(sud, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(x,y);
        setVisible(true);
    }
}
