package it.progettojorick.view;

import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Amministratore;
import it.progettojorick.model.RichiestaRegistrazione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmministratoreFrame extends JFrame {

    private int x=500;
    private int y=150;

    Amministratore a = (Amministratore)SessionManager.getInstance().getSession().get("amministratore");

    public AmministratoreFrame() {
        super("finestra amministratore");


    Container c = getContentPane();

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2-100);

        c.setLayout(new BorderLayout());

        JPanel centro = new JPanel();
        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
        JLabel benvenuto = new JLabel("Benvenuto amministratore "+a.getNome()+"!");
        centro.add(benvenuto);

        JButton richiesteReg = new JButton("Gestisci registrazioni");
        JButton ordini = new JButton("Gestisci ordini");// Sistemare action listener
        JButton btnLogout = new JButton("Logout");
        sud.add(btnLogout);

        richiesteReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 AmministratoreFrame a = (AmministratoreFrame)SessionManager.getInstance().getSession().get("finestra_amministratore");
                 a.setVisible(false);

                 try
                 {RichiestaRegistrazioneFrame reg= new RichiestaRegistrazioneFrame();
                 SessionManager.getInstance().getSession().put("finestra_richieste_registrazione", reg);
                 }catch (Exception ex){
                     System.out.println("error");
                     JOptionPane.showMessageDialog(null, "Non sono presenti richieste di registrazione.");
                     a.setVisible(true);
                 }
            }
        });

        AmministratoreFrame _this = this;
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SessionManager.getInstance().getSession().put("amministratore", null);
                _this.setVisible(false);

                LoginFrame finestraLogin = new LoginFrame();
                SessionManager.getInstance().getSession().put("finestra_login", finestraLogin);

            }
        });

        sud.add(richiesteReg);
        sud.add(ordini);

        c.add(centro, BorderLayout.CENTER);             // pattern command
        c.add(sud,BorderLayout.SOUTH);


        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
