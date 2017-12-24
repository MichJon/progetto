package it.progettojorick.view;

import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Amministratore;
import it.progettojorick.model.RichiestaRegistrazione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmministratoreFrame extends JFrame {

    private int x=550;
    private int y=130;




    public AmministratoreFrame() {
        super("finestra amministratore");

        AmministratoreFrame _this = this;

        Amministratore a = (Amministratore)SessionManager.getInstance().getSession().get("amministratore");

        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2-100);

        c.setLayout(new BorderLayout());

        JPanel nord = new JPanel(new FlowLayout());
        JPanel sud = new JPanel(new FlowLayout());
        GridLayout GL = new GridLayout(1,3);
        GL.setHgap(15);
        JPanel Pbuttons = new JPanel(GL);

        JLabel benvenuto = new JLabel("Benvenuto amministratore "+a.getNome()+"!");
        benvenuto.setFont(new Font("Serif", Font.PLAIN, 25));
        JButton richiesteReg = new JButton("Gestisci registrazioni");
        JButton ordini = new JButton("Gestisci ordini");
        JButton btnLogout = new JButton("Logout");
        Pbuttons.add(new JPanel(new FlowLayout()).add(btnLogout));
        Pbuttons.add(new JPanel(new FlowLayout()).add(richiesteReg));
        Pbuttons.add(new JPanel(new FlowLayout()).add(ordini));

        ordini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                _this.setVisible(false);
                OrdiniAmministratoreFrame oafr= new OrdiniAmministratoreFrame();
                SessionManager.getInstance().getSession().put("finestra_gestione_ordini",oafr);

            }
        });

        richiesteReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 //AmministratoreFrame a = (AmministratoreFrame)SessionManager.getInstance().getSession().get("finestra_amministratore");
                 //a.setVisible(false);
                _this.setVisible(false);

                 try
                 {RichiestaRegistrazioneFrame reg= new RichiestaRegistrazioneFrame();
                 SessionManager.getInstance().getSession().put("finestra_richieste_registrazione", reg);
                 }catch (Exception ex){
                     System.out.println("error");
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

        nord.add(benvenuto);
        sud.add(Pbuttons);

        c.add(nord, BorderLayout.NORTH);
        c.add(sud,BorderLayout.SOUTH);
        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
