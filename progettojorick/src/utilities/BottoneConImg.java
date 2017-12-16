package utilities;

import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.dao.mysql.ProdottoDAO;
import it.progettojorick.model.Prodotto;
import it.progettojorick.view.InfoProdottoFrame;
import it.progettojorick.view.ProdottoFrame;
import it.progettojorick.view.UtenteFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class BottoneConImg extends JPanel {

    private String url;
    private String nomeFile;
    private String nomeProdotto;
    private String prezzo;

    public BottoneConImg(String nomeFile, String nomeProdotto, String prezzo) {
        super();
        super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        super.setVisible(true);
        this.nomeFile = nomeFile;
        this.nomeProdotto=nomeProdotto;
        this.prezzo= prezzo;




        ImageIcon img = new ImageIcon("./images/"+nomeFile);
        Image image = img.getImage().getScaledInstance(70,60,0);
        ImageIcon newImg = new ImageIcon(image);
        JButton b = new JButton();
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UtenteFrame utfr = (UtenteFrame)SessionManager.getInstance().getSession().get("finestra_utente");
                utfr.setVisible(false);
                Prodotto pr= ProdottoDAO.getInstance().findByName(nomeProdotto); //inserire nel business
                InfoProdottoFrame prfr=new InfoProdottoFrame(pr);
                prfr.setVisible(true);

            }
        });
        JLabel lblNome = new JLabel(nomeProdotto);
        JLabel lblPrezzo = new JLabel("Prezzo: €"+ prezzo);

        //JPanel p = new JPanel(new FlowLayout());

       // centro.add(p);
       // b.setSize(1,1);
       // ImageIcon img = new ImageIcon(imgb.getScaledInstance(70,60,0));
        b.setIcon(newImg);
        super.add(b);
        super.add(lblNome);
        super.add(lblPrezzo);

        b.setContentAreaFilled(false);
    //p.setVisible(true);
    }
}
