package it.progettojorick.view;

import it.progettojorick.business.RichiestaOrdineBusiness;
import it.progettojorick.business.RichiestaRegistrazioneBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Amministratore;
import it.progettojorick.model.RichiestaRegistrazione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmministratoreFrame extends JFrame {

    private int x=550;
    private int y=340;




    public AmministratoreFrame() {
        super("Finestra amministratore");

        AmministratoreFrame _this = this;

        Amministratore a = (Amministratore)SessionManager.getInstance().getSession().get("amministratore");

        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        c.setLayout(new BorderLayout());

        JPanel nord = new JPanel(new BorderLayout());
        JPanel centro = new JPanel(new GridLayout(2,1));
        JPanel sud = new JPanel(new FlowLayout());
        GridLayout GL = new GridLayout(1,3);
        GL.setHgap(15);
        JPanel Pbuttons = new JPanel(GL);

        JLabel benvenuto = new JLabel("Benvenuto amministratore ", SwingConstants.CENTER);
        benvenuto.setFont(new Font("Serif", Font.PLAIN, 25));
        JLabel nomeAmm = new JLabel(a.getNome()+" "+a.getCognome(), SwingConstants.CENTER);
        nomeAmm.setFont(new Font("Serif", Font.PLAIN, 30));
        JButton btnrichiesteReg = new JButton("Gestisci registrazioni");
        JButton btnordini = new JButton("Gestisci ordini");
        JButton btnLogout = new JButton("Logout");
        Pbuttons.add(btnLogout);
        Pbuttons.add(btnrichiesteReg);
        Pbuttons.add(btnordini);

        btnordini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(RichiestaOrdineBusiness.getInstance().richiestePresenti()!=null){
                _this.setVisible(false);
                OrdiniAmministratoreFrame oafr= new OrdiniAmministratoreFrame();
                SessionManager.getInstance().getSession().put("finestra_gestione_ordini",oafr);
                }else{
                    // System.out.println("error");
                    JOptionPane.showMessageDialog(null, "Non sono presenti ordini.");
                    _this.setVisible(true);
                    //a.setVisible(true);
                }

            }
        });

        btnrichiesteReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //AmministratoreFrame a = (AmministratoreFrame)SessionManager.getInstance().getSession().get("finestra_amministratore");
                 //a.setVisible(false);


                if (RichiestaRegistrazioneBusiness.getInstance().richiestePresenti()!=null) {
                    _this.setVisible(false);
                    RichiestaRegistrazioneFrame reg = new RichiestaRegistrazioneFrame();
                    SessionManager.getInstance().getSession().put("finestra_richieste_registrazione", reg);
                }else{
                    // System.out.println("error");
                     JOptionPane.showMessageDialog(null, "Non sono presenti richieste di registrazione.");
                     _this.setVisible(true);
                     //a.setVisible(true);
                 }
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SessionManager.getInstance().getSession().put("amministratore", null);
                _this.setVisible(false);

                LoginFrame finestraLogin = new LoginFrame();
                SessionManager.getInstance().getSession().put("finestra_login", finestraLogin);

            }
        });

        JPanel immagine = new JPanel(new BorderLayout());
        ImageIcon img = new ImageIcon("./images/LOGO2.jpg");
        Image image = img.getImage().getScaledInstance(525,100,0);
        ImageIcon newImg = new ImageIcon(image);
        immagine.add(new JLabel(newImg), BorderLayout.CENTER);

        nord.add(immagine);
        centro.add(benvenuto);
        centro.add(nomeAmm);
        sud.add(Pbuttons);

        nord.setBackground(Color.white);
        centro.setBackground(Color.white);
        Pbuttons.setBackground(Color.white);
        sud.setBackground(Color.white);

        c.add(nord, BorderLayout.NORTH);
        c.add(centro, BorderLayout.CENTER);
        c.add(sud,BorderLayout.SOUTH);
        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
