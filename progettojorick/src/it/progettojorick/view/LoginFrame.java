package it.progettojorick.view;

import it.progettojorick.actionListeners.LoginListener;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtEmail = new JTextField();
    private JPasswordField txtPassword = new JPasswordField();
    private int x=500;
    private int y=150;

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

  /*  c.add(new JLabel("Buona lezione"));
    c.add(new JButton("Cliccami"));*/
    //c.setLayout(new FlowLayout());
/*
    c.setLayout(new GridLayout(4,4));

    for(int i=0; i<10; i++)
        c.add(new JButton(String.valueOf(i)));
*/
/*
    c.setLayout(new BorderLayout());
    c.add(new JLabel("Buona lezione"), BorderLayout.NORTH);
    c.add(new JButton("cliccami"), BorderLayout.SOUTH);
    c.add(new JTextField(), BorderLayout.CENTER);
    c.add(new JPasswordField(), BorderLayout.CENTER);
*/


    c.setLayout(new BorderLayout());
    LoginListener listener = new LoginListener(this);
    JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(2,2));
       JLabel lblEmail = new JLabel("Email");
       JLabel lblPassword = new JLabel("Password");

        centro.add(lblEmail);
        centro.add(txtEmail);
        centro.add(lblPassword);
        centro.add(txtPassword);
        txtEmail.addActionListener(listener);
        txtPassword.addActionListener(listener);

JPanel nord = new JPanel();
    nord.setLayout(new FlowLayout());
    nord.add(new JLabel("Benvenuto Utente!"));

    JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
       // sud.add(new JLabel("Benvenuto"));
        JButton btnlogin = new JButton("LOGIN");
        btnlogin.addActionListener(listener);
        sud.add(btnlogin);


    c.add(centro, BorderLayout.CENTER);             // pattern command
    c.add(sud,BorderLayout.SOUTH);
    c.add(nord,BorderLayout.NORTH);

   /* JMenuBar bar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenu move = new JMenu("Move");
        move.add(new JMenuItem("Up"));
        move.add(new JMenuItem("Down"));
    edit.add(move);
    JMenuItem random = new JMenuItem("Random");
    random.addActionListener(listener);
    random.setActionCommand("RANDOM_MENU_ITEM");
    edit.add(random);
    bar.add(file);
    bar.add(edit);


    setJMenuBar(bar);*/

    setSize(x, y);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    }

    //promemoria classi annidate
    class Mia{

    }
}
