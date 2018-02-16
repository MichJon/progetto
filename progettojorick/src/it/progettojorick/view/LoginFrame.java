package it.progettojorick.view;

import it.progettojorick.actionListeners.LoginListener;
import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.SessionManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JTextField txtEmail = new JTextField();
    private JPasswordField txtPassword = new JPasswordField();
    private int x=500;
    private int y=250;

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(JPasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public LoginFrame() {

        super("Prima finestra");

        LoginFrame _this = this;

        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2-100);

        Border blackline;
        blackline = BorderFactory.createLineBorder(Color.black);

        c.setLayout(new BorderLayout());
        GridLayout GL = new GridLayout(1,2);
        GL.setHgap(15);
        LoginListener listener = new LoginListener(this);

        JPanel nord = new JPanel(new FlowLayout());
        JPanel centro = new JPanel(new GridLayout(2,1));
        JPanel sud = new JPanel(new FlowLayout());
        JPanel Pbuttons = new JPanel(GL);

        TitledBorder Temail;
        Temail = BorderFactory.createTitledBorder(blackline,"Email");
        TitledBorder Tpassword;
        Tpassword = BorderFactory.createTitledBorder(blackline,"Password");
        txtEmail.setBorder(Temail);
        txtPassword.setBorder(Tpassword);

        JLabel benvenuto = new JLabel("Benvenuto Utente!");
        benvenuto.setFont(new Font("Serif", Font.PLAIN, 18));

        JButton btnlogin = new JButton("LOGIN");
        JButton btnRegistrazione = new JButton("REGISTRATI");
        btnRegistrazione.addActionListener(listener);
        btnlogin.addActionListener(listener);

        nord.add(benvenuto);
        centro.add(txtEmail);
        centro.add(txtPassword);
        txtEmail.addActionListener(listener);
        txtPassword.addActionListener(listener);
        Pbuttons.add(btnRegistrazione);
        Pbuttons.add(btnlogin);
        sud.add(Pbuttons);

        JButton btnGuest = new JButton("Entra come ospite");
        btnGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                _this.setVisible(false);

                UtenteFrame utFr = new UtenteFrame(ProdottoBusiness.getInstance().prodottiPresenti());
                SessionManager.getInstance().getSession().put("finestra_utente", utFr);
                SessionManager.getInstance().getSession().put("utente", null);

            }
        });
        Pbuttons.add(btnGuest);

        c.add(nord,BorderLayout.NORTH);
        c.add(centro, BorderLayout.CENTER);
        c.add(sud,BorderLayout.SOUTH);

    setSize(x, y);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    }

}
