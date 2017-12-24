package it.progettojorick.view;

import it.progettojorick.business.PagamentoBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Pagamento;
import it.progettojorick.model.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.IOException;

public class PagamentoDatiFrame extends JFrame {

    private int x = 400;
    private int y = 250;

    private JTextField txtNumCarta=new JTextField();
    private JTextField txtCodSicurezza=new JTextField();
    private JTextField txtDataScadenza=new JTextField();
    JComboBox comboBoxCircuito = new JComboBox();

    Utente u = (Utente)SessionManager.getInstance().getSession().get("utente");


    public JTextField getTxtNumCarta() {
        return txtNumCarta;
    }

    public void setTxtNumCarta(JTextField txtNumCarta) {
        this.txtNumCarta = txtNumCarta;
    }

    public JComboBox getComboBoxCircuito() {
        return comboBoxCircuito;
    }

    public void setComboBoxCircuito(JComboBox comboBoxCircuito) {
        this.comboBoxCircuito = comboBoxCircuito;
    }

    public JTextField getTxtCodSicurezza() {
        return txtCodSicurezza;
    }

    public void setTxtCodSicurezza(JTextField txtCodSicurezza) {
        this.txtCodSicurezza = txtCodSicurezza;
    }

    public JTextField getTxtDataScadenza() {
        return txtDataScadenza;
    }

    public void setTxtDataScadenza(JTextField txtDataScadenza) {
        this.txtDataScadenza = txtDataScadenza;
    }

    public PagamentoDatiFrame() {

        super("Finestra dati pagamento");

        PagamentoDatiFrame _this = this;

        Container RegPane = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        RegPane.setLayout(new BorderLayout());
       // CategoriaListener listener = new CategoriaListener(this);

        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(4,2));


        JLabel lblNumCarta = new JLabel("Numero carta");
        JLabel lblCircuito = new JLabel("Circuito");
        JComboBox comboBoxCircuito = new JComboBox();
        JLabel lblCodSicurezza = new JLabel("CVV");
        JLabel lblDataScadenza = new JLabel("Data scadenza");

        comboBoxCircuito.addItem("Mastercard");
        comboBoxCircuito.addItem("Visa");
        comboBoxCircuito.addItem("Paypal");
        comboBoxCircuito.addItem("Postepay");

        centro.add(lblNumCarta);
        centro.add(txtNumCarta);
        centro.add(lblCircuito);
        centro.add(comboBoxCircuito);
        centro.add(lblCodSicurezza);
        centro.add(txtCodSicurezza);
        centro.add(lblDataScadenza);
        centro.add(txtDataScadenza);





        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
        JButton btnConferma = new JButton("Conferma");
       // btnConferma.addActionListener(listener);
        sud.add(btnConferma);
        btnConferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


              try {
                  long numcarta = Long.parseLong(_this.getTxtNumCarta().getText());


                  String circuito = (String) comboBoxCircuito.getSelectedItem();

                  int cvv = Integer.parseInt(_this.getTxtCodSicurezza().getText());
                  String data = _this.getTxtDataScadenza().getText();
                  PagamentoBusiness.getInstance().inserisciPagamento(numcarta, circuito, cvv, data);
                  Pagamento p = PagamentoBusiness.getInstance().trovaPagamento(numcarta);
                  PagamentoBusiness.getInstance().inserisciPagamentoUtente(u, p);
                  _this.dispose();
                  new PagamentiSalvatiFrame();
              }catch (Exception ex){
                  JOptionPane.showMessageDialog(null,"Errore. Controllare i dati inseriti.");
              }
            }
        });

        JPanel nord = new JPanel();
        nord.setLayout(new FlowLayout());
        nord.add(new JLabel("Inserisci i dati del pagamento:"));

        RegPane.add(centro, BorderLayout.CENTER);             // pattern command
        RegPane.add(sud,BorderLayout.SOUTH);
        RegPane.add(nord,BorderLayout.NORTH);

        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
