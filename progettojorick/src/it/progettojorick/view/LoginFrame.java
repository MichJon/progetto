package it.progettojorick.view;

import it.progettojorick.actionListeners.LoginListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtEmail = new JTextField();
    private JPasswordField txtPassword = new JPasswordField();
    private int x=300;
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

        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2-100);

        Border blackline;
        blackline = BorderFactory.createLineBorder(Color.black);

        c.setLayout(new BorderLayout());
        LoginListener listener = new LoginListener(this);

        JPanel nord = new JPanel();
        nord.setLayout(new FlowLayout());
        JLabel benvenuto = new JLabel("Benvenuto Utente!");
        benvenuto.setFont(new Font("Serif", Font.PLAIN, 18));
        nord.add(benvenuto);


        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(2,1));
        TitledBorder Temail;
        Temail = BorderFactory.createTitledBorder(blackline,"Email");
        TitledBorder Tpassword;
        Tpassword = BorderFactory.createTitledBorder(blackline,"Password");
        txtEmail.setBorder(Temail);
        txtPassword.setBorder(Tpassword);

        centro.add(txtEmail);
        centro.add(txtPassword);
        txtEmail.addActionListener(listener);
        txtPassword.addActionListener(listener);


        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
        JButton btnlogin = new JButton("LOGIN");
        JButton btnRegistrazione = new JButton("REGISTRATI");

        btnRegistrazione.addActionListener(listener);
        btnlogin.addActionListener(listener);
        sud.add(btnRegistrazione);
        sud.add(new JSeparator(SwingConstants.VERTICAL));
        sud.add(new JSeparator(SwingConstants.VERTICAL));
        sud.add(new JSeparator(SwingConstants.VERTICAL));
        sud.add(new JSeparator(SwingConstants.VERTICAL));
        sud.add(btnlogin);

        c.add(nord,BorderLayout.NORTH);
        c.add(centro, BorderLayout.CENTER);
        c.add(sud,BorderLayout.SOUTH);


    setSize(x, y);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    }

}
