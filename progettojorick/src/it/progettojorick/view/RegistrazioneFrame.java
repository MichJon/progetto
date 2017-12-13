package it.progettojorick.view;

import it.progettojorick.actionListeners.LoginListener;
import it.progettojorick.actionListeners.RegistrazioneListener;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;

public class RegistrazioneFrame extends JFrame {

    private JTextField txtNome = new JTextField();
    private JTextField txtCognome = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtPassword = new JTextField();
    private JTextField txtIndirizzo = new JTextField();
    private JTextField txtNumTelefono = new JTextField();


    private int x = 400;
    private int y = 350;

    public JTextField getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    public JTextField getTxtCognome() {
        return txtCognome;
    }

    public void setTxtCognome(JTextField txtCognome) {
        this.txtCognome = txtCognome;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JTextField getTxtIndirizzo() {
        return txtIndirizzo;
    }

    public void setTxtIndirizzo(JTextField txtIndirizzo) {
        this.txtIndirizzo = txtIndirizzo;
    }

    public JTextField getTxtNumTelefono() {
        return txtNumTelefono;
    }

    public void setTxtNumTelefono(JTextField txtNumTelefono) {
        this.txtNumTelefono = txtNumTelefono;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(JTextField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public RegistrazioneFrame() {

        super("Finestra di Registrazione");

        Container RegPane = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        RegPane.setLayout(new BorderLayout());
        RegistrazioneListener listener = new RegistrazioneListener(this);

        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(6,2));

        JLabel lblNome = new JLabel("Nome");
        JLabel lblCognome = new JLabel("Cognome");
        JLabel lblEmail = new JLabel("Email");
        JLabel lblPassword = new JLabel("Password");
        JLabel lblIndirizzo = new JLabel("Indirizzo");
        JLabel lblNumtelefono = new JLabel("Numero di telefono");

        centro.add(lblNome);
        centro.add(txtNome);
        centro.add(lblCognome);
        centro.add(txtCognome);
        centro.add(lblEmail);
        centro.add(txtEmail);
        centro.add(lblPassword);
        centro.add(txtPassword);
        centro.add(lblIndirizzo);
        centro.add(txtIndirizzo);
        centro.add(lblNumtelefono);
        centro.add(txtNumTelefono);

        txtNome.addActionListener(listener);
        txtCognome.addActionListener(listener);
        txtEmail.addActionListener(listener);
        txtPassword.addActionListener(listener);
        txtIndirizzo.addActionListener(listener);
        txtNumTelefono.addActionListener(listener);

        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
        JButton btnRegistrazione = new JButton("REGISTRATI");
        btnRegistrazione.addActionListener(listener);
        sud.add(btnRegistrazione);

        JPanel nord = new JPanel();
        nord.setLayout(new FlowLayout());
        nord.add(new JLabel(" Compila i campi seguenti con i dati richiesti "));

        RegPane.add(centro, BorderLayout.CENTER);             // pattern command
        RegPane.add(sud,BorderLayout.SOUTH);
        RegPane.add(nord,BorderLayout.NORTH);

        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
    }

}
