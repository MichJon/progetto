package it.progettojorick.view;

import it.progettojorick.actionListeners.LoginListener;
import it.progettojorick.actionListeners.RegistrazioneListener;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrazioneFrame extends JFrame {

    private JTextField txtNome = new JTextField();
    private JTextField txtCognome = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtPassword = new JTextField();
    private JTextField txtIndirizzo = new JTextField();
    private JTextField txtNumTelefono = new JTextField();


    private int x = 325;
    private int y = 400;

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

        RegistrazioneFrame _this = this;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        RegPane.setLayout(new BorderLayout());
        RegistrazioneListener listener = new RegistrazioneListener(this);

        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(6,2));
        Border blackline;
        blackline = BorderFactory.createLineBorder(Color.black);

        TitledBorder Tnome,Tcognome,Temail,Tpassword,Tindirizzo,Tnumtelefono;
        Tnome = BorderFactory.createTitledBorder(blackline,"Nome");
        Tcognome = BorderFactory.createTitledBorder(blackline,"Cognome");
        Temail = BorderFactory.createTitledBorder(blackline,"Email");
        Tpassword = BorderFactory.createTitledBorder(blackline,"Password");
        Tindirizzo = BorderFactory.createTitledBorder(blackline,"Indirizzo");
        Tnumtelefono = BorderFactory.createTitledBorder(blackline,"Numero di telefono");

        txtNome.setBorder(Tnome);
        txtCognome.setBorder(Tcognome);
        txtEmail.setBorder(Temail);
        txtPassword.setBorder(Tpassword);
        txtIndirizzo.setBorder(Tindirizzo);
        txtNumTelefono.setBorder(Tnumtelefono);

        centro.add(txtNome);
        centro.add(txtCognome);
        centro.add(txtEmail);
        centro.add(txtPassword);
        centro.add(txtIndirizzo);
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
        JButton btnIndietro = new JButton("Indietro");
        btnIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                new LoginFrame();
            }
        });
        sud.add(btnIndietro);
        sud.add(new JSeparator(SwingConstants.VERTICAL));
        sud.add(new JSeparator(SwingConstants.VERTICAL));
        sud.add(new JSeparator(SwingConstants.VERTICAL));
        sud.add(new JSeparator(SwingConstants.VERTICAL));
        sud.add(btnRegistrazione);

        JPanel nord = new JPanel();
        nord.setLayout(new FlowLayout());
        JLabel intro = new JLabel(" Compila i campi seguenti con i dati richiesti ");
        intro.setFont(new Font("Serif", Font.PLAIN, 17));
        nord.add(intro);

        RegPane.add(centro, BorderLayout.CENTER);             // pattern command
        RegPane.add(sud,BorderLayout.SOUTH);
        RegPane.add(nord,BorderLayout.NORTH);

        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
    }

}
