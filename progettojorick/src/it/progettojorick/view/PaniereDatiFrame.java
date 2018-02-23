package it.progettojorick.view;

import it.progettojorick.business.PaniereBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PaniereDatiFrame extends JFrame {

    private JTextField txtNome=new JTextField();

    private int x = 400;
    private int y = 130;

    Utente u = (Utente)SessionManager.getInstance().getSession().get("utente");

    public JTextField getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    public PaniereDatiFrame()  {

        super("Finestra creazione paniere");

        Container RegPane = getContentPane();

        PaniereDatiFrame _this=this;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        RegPane.setLayout(new BorderLayout());
       // CategoriaListener listener = new CategoriaListener(this);

        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(1,2));


        JLabel lblNome = new JLabel("Nome Paniere");





        JPanel pan = new JPanel();




        centro.add(lblNome);
        centro.add(txtNome);

        centro.add(pan);




        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
        JButton btnConferma = new JButton("Crea");
        btnConferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!txtNome.getText().equals("")) {
                    PaniereBusiness.getInstance().inserisciPaniere(txtNome.getText(), u);
                    _this.dispose();
                    ElencoPanieriView epfr = (ElencoPanieriView) SessionManager.getInstance().getSession().get("finestra_elenco_panieri");
                    epfr.dispose();
                    new ElencoPanieriView();
                }else JOptionPane.showMessageDialog(null, "Inserire un nome.");

            }
        });


        JButton annulla = new JButton("Annulla");
        annulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
            }
        });


        sud.add(annulla);
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
