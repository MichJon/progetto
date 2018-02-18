package it.progettojorick.view;

import it.progettojorick.actionListeners.LoginListener;
import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.SessionManager;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoginFrame extends JFrame {

    private JTextField txtEmail = new JTextField();
    private JPasswordField txtPassword = new JPasswordField();
    private int x=550;
    private int y=450;

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

        super("Finestra Login"); //Prima finestra

        LoginFrame _this = this;

        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        Border blackline;
        blackline = BorderFactory.createLineBorder(Color.black);

        c.setLayout(new BorderLayout());
        GridLayout GL = new GridLayout(1,1);
        GL.setHgap(20);
        LoginListener listener = new LoginListener(this);

        JPanel nord = new JPanel(new BorderLayout());
        JPanel centro = new JPanel(new GridLayout(3,1));
        JPanel sud = new JPanel(new FlowLayout());
        JPanel Pbuttons = new JPanel(GL);

        TitledBorder Temail;
        Temail = BorderFactory.createTitledBorder(blackline,"Email");
        TitledBorder Tpassword;
        Tpassword = BorderFactory.createTitledBorder(blackline,"Password");
        txtEmail.setBorder(Temail);
        txtPassword.setBorder(Tpassword);

        JLabel benvenuto = new JLabel("Benvenuto Utente!", SwingConstants.CENTER);
        benvenuto.setFont(new Font("Serif", Font.PLAIN,30));
        //benvenuto.setHorizontalAlignment();

        JButton btnlogin = new JButton("LOGIN");
        JButton btnRegistrazione = new JButton("REGISTRATI");
        btnRegistrazione.addActionListener(listener);
        btnlogin.addActionListener(listener);
        JButton EE = new JButton(" ");
        EE.setBackground(Color.white);
        EE.setBorder(BorderFactory.createLineBorder(Color.white));
        EE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String soundName = "test.wav";
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                } catch (UnsupportedAudioFileException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Clip clip = null;
                try {
                    clip = AudioSystem.getClip();
                } catch (LineUnavailableException e1) {
                    e1.printStackTrace();
                }
                try {
                    clip.open(audioInputStream);
                } catch (LineUnavailableException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                clip.start();
            }
        });

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

        JPanel immagine = new JPanel(new BorderLayout());
        ImageIcon img = new ImageIcon("./images/LOGO2.jpg");
        Image image = img.getImage().getScaledInstance(500,150,0);
        ImageIcon newImg = new ImageIcon(image);
        immagine.add(new JLabel(newImg), BorderLayout.CENTER);
        //pack();

        immagine.setBackground(Color.white);
        nord.setBackground(Color.white);
        centro.setBackground(Color.white);
        Pbuttons.setBackground(Color.white);
        sud.setBackground(Color.white);

        nord.add(immagine);
        centro.add(benvenuto);
        centro.add(txtEmail);
        centro.add(txtPassword);
        txtEmail.addActionListener(listener);
        txtPassword.addActionListener(listener);
        Pbuttons.add(btnRegistrazione);
        Pbuttons.add(btnlogin);
        Pbuttons.add(btnGuest);
        sud.add(EE);
        sud.add(Pbuttons);

        c.add(nord,BorderLayout.NORTH);
        c.add(centro, BorderLayout.CENTER);
        c.add(sud,BorderLayout.SOUTH);

    setSize(x, y);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    }

}
