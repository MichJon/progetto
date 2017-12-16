package it.progettojorick.view;

import it.progettojorick.actionListeners.ProdottoListener;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Prodotto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

public class ProdottoFrame extends JFrame{


    private JTextField txtNome = new JTextField();
    private JTextField txtDescrizone = new JTextField();
    private JTextField txtPrezzo = new JTextField();
    private JTextField txtQuantita = new JTextField();
    private JTextField txtCategoria = new JTextField();
    private JTextField txtProduttore = new JTextField();
    private JTextField txtDistributore = new JTextField();
    private JTextField txtImgUrl = new JTextField();
    private JLabel lblUrl=new JLabel();
    private JLabel lblNomeFile = new JLabel();
   // private Path path;

    private int x = 400;
    private int y = 350;

    public JLabel getLblNomeFile() {
        return lblNomeFile;
    }

    public void setLblNomeFile(JLabel lblNomeFile) {
        this.lblNomeFile = lblNomeFile;
    }

    public JLabel getLblUrl() {
        return lblUrl;
    }

    public void setLblUrl(JLabel lblUrl) {
        this.lblUrl = lblUrl;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    public JTextField getTxtDescrizone() {
        return txtDescrizone;
    }

    public void setTxtDescrizone(JTextField txtDescrizone) {
        this.txtDescrizone = txtDescrizone;
    }

    public JTextField getTxtPrezzo() {
        return txtPrezzo;
    }

    public void setTxtPrezzo(JTextField txtPrezzo) {
        this.txtPrezzo = txtPrezzo;
    }

    public JTextField getTxtQuantita() {
        return txtQuantita;
    }

    public void setTxtQuantita(JTextField txtQuantita) {
        this.txtQuantita = txtQuantita;
    }

    public JTextField getTxtCategoria() {
        return txtCategoria;
    }

    public void setTxtCategoria(JTextField txtCategoria) {
        this.txtCategoria = txtCategoria;
    }

    public JTextField getTxtProduttore() {
        return txtProduttore;
    }

    public void setTxtProduttore(JTextField txtProduttore) {
        this.txtProduttore = txtProduttore;
    }

    public JTextField getTxtDistributore() {
        return txtDistributore;
    }

    public void setTxtDistributore(JTextField txtDistributore) {
        this.txtDistributore = txtDistributore;
    }

    public JTextField getTxtImgUrl() {
        return txtImgUrl;
    }

    public void setTxtImgUrl(JTextField txtImgUrl) {
        this.txtImgUrl = txtImgUrl;
    }


    public ProdottoFrame() {

        super("Finestra prodotto");

        Container RegPane = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        RegPane.setLayout(new BorderLayout());
        ProdottoListener listener = new ProdottoListener(this);

        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(8,2));

        JLabel lblNome = new JLabel("Nome");
        JLabel lblDescrizione = new JLabel("Descrizione");
        JLabel lblPrezzo = new JLabel("Prezzo");
        JLabel lblQuantita = new JLabel("Quantita");
        JLabel lblCategoria = new JLabel("Categoria");
        JLabel lblProduttore = new JLabel("Produttore");
        JLabel lblDistributore = new JLabel("Distributore");
        JLabel lblImgUrl = new JLabel("Url immagine");


        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(1,2));
        JButton sfoglia = new JButton("Sfoglia");

        ProdottoFrame _this = this;
        sfoglia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileChooser fc=new FileChooser();
               // _this.setLblUrl(new JLabel(fc.getUrl()));
                _this.setLblNomeFile( new JLabel(fc.getNomeFile()));
               // pan.add(lblUrl);
                pan.add(lblNomeFile);
                _this.setVisible(false);
                _this.setVisible(true);


            }
        });

        centro.add(lblNome);
        centro.add(txtNome);
        centro.add(lblDescrizione);
        centro.add(txtDescrizone);
        centro.add(lblPrezzo);
        centro.add(txtPrezzo);
        centro.add(lblQuantita);
        centro.add(txtQuantita);
        centro.add(lblCategoria);
        centro.add(txtCategoria);
        centro.add(lblProduttore);
        centro.add(txtProduttore);
        centro.add(lblDistributore);
        centro.add(txtDistributore);
        centro.add(lblImgUrl);
        //pan.add(lblUrl);
        pan.add(sfoglia);
        centro.add(pan);


//        txtNome.addActionListener(listener);
//        txtCognome.addActionListener(listener);
//        txtEmail.addActionListener(listener);
//        txtPassword.addActionListener(listener);
//        txtIndirizzo.addActionListener(listener);
//        txtNumTelefono.addActionListener(listener);

        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
        JButton btnConferma = new JButton("Crea");
        btnConferma.addActionListener(listener);
        sud.add(btnConferma);

        JPanel nord = new JPanel();
        nord.setLayout(new FlowLayout());
        nord.add(new JLabel(" Compila i campi seguenti con i dati richiesti "));

        RegPane.add(centro, BorderLayout.CENTER);             // pattern command
        RegPane.add(sud,BorderLayout.SOUTH);
        RegPane.add(nord,BorderLayout.NORTH);

        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



}
