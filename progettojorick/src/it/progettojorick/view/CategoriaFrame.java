package it.progettojorick.view;

import it.progettojorick.actionListeners.CategoriaListener;
import it.progettojorick.actionListeners.ProdottoListener;
import utilities.BottoneConImg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CategoriaFrame extends JFrame{

    private JTextField txtNome=new JTextField();

    private int x = 400;
    private int y = 130;

    public JTextField getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    public CategoriaFrame() throws IOException {

        super("Finestra categoria");

        CategoriaFrame _this=this;
        Container RegPane = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        RegPane.setLayout(new BorderLayout());
        CategoriaListener listener = new CategoriaListener(this);

        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(1,2));


        JLabel lblNome = new JLabel("Nome Categoria");





        JPanel pan = new JPanel();




        centro.add(lblNome);
        centro.add(txtNome);

        centro.add(pan);




        JPanel sud = new JPanel();
        sud.setLayout(new FlowLayout());
        JButton btnConferma = new JButton("Crea");
        btnConferma.addActionListener(listener);


        JButton indietro = new JButton("Indietro");
        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                new ListaCategorieFrame();
            }
        });
        sud.add(indietro);
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
