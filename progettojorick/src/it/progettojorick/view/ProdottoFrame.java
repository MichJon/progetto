package it.progettojorick.view;

import it.progettojorick.actionListeners.ProdottoListener;
import it.progettojorick.business.CategoriaBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.dao.mysql.DistributoreDAO;
import it.progettojorick.dao.mysql.ProduttoreDAO;
import it.progettojorick.model.Categoria;
import it.progettojorick.model.Distributore;
import it.progettojorick.model.Prodotto;
import it.progettojorick.model.Produttore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

public class ProdottoFrame extends JFrame{


    private JTextField txtNome = new JTextField();
    private JTextField txtDescrizone = new JTextField();
    private JTextField txtPrezzo = new JTextField();
    private JTextField txtDisponibilita = new JTextField();
    private JTextField txtCategoria = new JTextField();
    private JTextField txtProduttore = new JTextField();
    private JTextField txtDistributore = new JTextField();
    private JTextField txtImgUrl = new JTextField();
    private JLabel lblUrl=new JLabel();
    private JLabel lblNomeFile = new JLabel();
    private JTextField txtSconto = new JTextField();
    private ArrayList<Prodotto> prodottiContenuti = new ArrayList<Prodotto>();
    private JComboBox cbCategoria = new JComboBox();
    private JComboBox cbProduttore = new JComboBox();
    private JComboBox cbDistributore = new JComboBox();
   // private Path path;

    private int x = 400;
    private int y = 400;


    public JComboBox getCbProduttore() {
        return cbProduttore;
    }

    public void setCbProduttore(JComboBox cbProduttore) {
        this.cbProduttore = cbProduttore;
    }

    public JComboBox getCbDistributore() {
        return cbDistributore;
    }

    public void setCbDistributore(JComboBox cbDistributore) {
        this.cbDistributore = cbDistributore;
    }

    public JComboBox getCbCategoria() {
        return cbCategoria;
    }



    public void setCbCategoria(JComboBox cbCategoria) {
        this.cbCategoria = cbCategoria;
    }

    public ArrayList<Prodotto> getProdottiContenuti() {
        return prodottiContenuti;
    }

    public void setProdottiContenuti(ArrayList<Prodotto> prodottiContenuti) {
        this.prodottiContenuti = prodottiContenuti;
    }

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

    public JTextField getTxtDisponibilita() {
        return txtDisponibilita;
    }

    public void setTxtDisponibilita(JTextField txtDisponibilita) {
        this.txtDisponibilita = txtDisponibilita;
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

    public JTextField getTxtSconto() {
        return txtSconto;
    }

    public void setTxtSconto(JTextField txtSconto) {
        this.txtSconto = txtSconto;
    }

    public ProdottoFrame(ArrayList<Prodotto> prodottiContenuti) {

        super("Finestra prodotto");

        this.prodottiContenuti=prodottiContenuti;

        Container c = getContentPane();
        JPanel prodottoPane = new JPanel();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        prodottoPane.setLayout(new BorderLayout());
        ProdottoListener listener = new ProdottoListener(this);

       // JTabbedPane tabbedPane = new JTabbedPane();
       // JScrollPane sp = new JScrollPane();
     //   c.add(sp);

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.PAGE_AXIS));
        JPanel centroNord = new JPanel(new GridLayout(9,2));


        JLabel lblNome = new JLabel("Nome");
        JLabel lblDescrizione = new JLabel("Descrizione");
        JLabel lblPrezzo = new JLabel("Prezzo");
        JLabel lblDisponibilita = new JLabel("Disponibilità");
        JLabel lblCategoria = new JLabel("Categoria");
        JLabel lblProduttore = new JLabel("Produttore");
        JLabel lblDistributore = new JLabel("Distributore");
        JLabel lblImgUrl = new JLabel("Url immagine");
        JLabel lblSconto = new JLabel("Sconto");



        ArrayList<Categoria> categorie= CategoriaBusiness.getInstance().categoriePresenti();

        Iterator j = categorie.iterator();

        while (j.hasNext()){

            Categoria cat = (Categoria) j.next();

            cbCategoria.addItem(cat.getNomecategoria());

        }

        ArrayList<Distributore> distributori= DistributoreDAO.getInstance().findAll();

        Iterator k = distributori.iterator();

        while (k.hasNext()){

            Distributore dist = (Distributore) k.next();

            cbDistributore.addItem(dist.getNome()+" (id:"+dist.getId()+")");

        }

        ArrayList<Produttore> produttori= ProduttoreDAO.getInstance().findAll();

        Iterator l = produttori.iterator();

        while (l.hasNext()){

            Produttore prod = (Produttore) l.next();

            cbProduttore.addItem(prod.getNome()+" (id:"+prod.getId()+")");

        }


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

        centroNord.add(lblNome);
        centroNord.add(txtNome);
        centroNord.add(lblDescrizione);
        centroNord.add(txtDescrizone);
        centroNord.add(lblPrezzo);
        centroNord.add(txtPrezzo);
        centroNord.add(lblDisponibilita);
        centroNord.add(txtDisponibilita);
        centroNord.add(lblCategoria);
        //centroNord.add(txtCategoria);
        centroNord.add(cbCategoria);
        centroNord.add(lblProduttore);
        //centroNord.add(txtProduttore);
        centroNord.add(cbProduttore);
        centroNord.add(lblDistributore);
        //centroNord.add(txtDistributore);
        centroNord.add(cbDistributore);
        centroNord.add(lblSconto);
        centroNord.add(txtSconto);
        centroNord.add(lblImgUrl);
        //pan.add(lblUrl);
        pan.add(sfoglia);
        centroNord.add(pan);


//        txtNome.addActionListener(listener);
//        txtCognome.addActionListener(listener);
//        txtEmail.addActionListener(listener);
//        txtPassword.addActionListener(listener);
//        txtIndirizzo.addActionListener(listener);
//        txtNumTelefono.addActionListener(listener);

        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
        JButton btnindietro = new JButton("Indietro");
        JButton btnConferma = new JButton("Crea");
        btnConferma.addActionListener(listener);
        sud.add(btnindietro);
        sud.add(btnConferma);

        btnindietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                new ListaProdottiFrame();
            }
        });

        JPanel nord = new JPanel();
        nord.setLayout(new FlowLayout());
        nord.add(new JLabel(" Compila i campi seguenti con i dati richiesti "));
        JPanel centroSud = new JPanel(new GridLayout(1,2));
        JPanel prodottiCont = new JPanel();
        prodottiCont.setLayout(new BoxLayout(prodottiCont, BoxLayout.PAGE_AXIS));

        if (prodottiContenuti!=null){
            centroSud.add(new JLabel("Prodotti contenuti"));

            Iterator i = prodottiContenuti.iterator();

            while(i.hasNext()){
                Prodotto p =(Prodotto) i.next();

                prodottiCont.add(new JLabel(p.getNome()));


            }
            centroSud.add(prodottiCont);

        }

        centro.add(centroNord);
        centro.add(centroSud);

        prodottoPane.add(centro, BorderLayout.CENTER);             // pattern command
     //   prodottoPane.add(centroSud, BorderLayout.CENTER);
        prodottoPane.add(sud,BorderLayout.SOUTH);
        prodottoPane.add(nord,BorderLayout.NORTH);

        //tabbedPane.add("Prodotto",prodottoPane);
      //  sp.add(prodottoPane);

        c.add(prodottoPane);
        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



}
