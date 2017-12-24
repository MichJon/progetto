package it.progettojorick.actionListeners;

import it.progettojorick.business.CarrelloBusiness;
import it.progettojorick.business.PersonaBusiness;
import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.dao.mysql.CarrelloDAO;
import it.progettojorick.model.*;
import it.progettojorick.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class LoginListener implements ActionListener {

    private LoginFrame finestra;

    public LoginListener(LoginFrame finestra){
        this.finestra=finestra;
    }

//    public LoginListener(){
//        this.finestra=(LoginFrame)SessionManager.getInstance().getSession().get("finestra_login");
//    }

  // LoginFrame finestra = (LoginFrame)SessionManager.getInstance().getSession().get("finestra_login");

    @Override
    public void actionPerformed(ActionEvent e) {

    System.out.println("Evento catturato!");


        if(e.getSource() instanceof JButton && ((JButton) e.getSource()).getText().equals("REGISTRATI") ) {

            finestra.setVisible(false);
            RegistrazioneFrame reg = new RegistrazioneFrame();
            reg.setVisible(true);
            SessionManager.getInstance().getSession().put("finestra_registrazione",reg);

        }

    else if(e.getSource() instanceof JButton || e.getSource() instanceof JTextField || e.getSource() instanceof JPasswordField ){
       // String username = "mario.rossi";
       // byte[] password = "Passw0rd1".getBytes();
        String email= finestra.getTxtEmail().getText();
        byte[] password = new String(finestra.getTxtPassword().getPassword()).getBytes();

        Persona p = PersonaBusiness.getInstance().login(email, password);
        SessionManager.getInstance().getSession().put("persona",p);


         if (p!=null) {
            System.out.println("LOGIN OK!");
            if (p instanceof Amministratore) {
                //apriremo la view dell'amministratore
                System.out.println("Benvenuto amministratore " + p.getNome() + " " + p.getCognome() + "!");
                Amministratore a = (Amministratore) p;
                System.out.println("email: " + a.getEmailAmministratore());
               // JOptionPane.showMessageDialog(null,"Benvenuto amministratore " + p.getNome() + " " + p.getCognome() + "!");
                SessionManager.getInstance().getSession().put("amministratore", a);
                //mostriamo la view dell'amministratore
                finestra.setVisible(false);
                AmministratoreFrame ammFrame = new AmministratoreFrame();
                ammFrame.setVisible(true);
                SessionManager.getInstance().getSession().put("finestra_amministratore", ammFrame);


            } else if (p instanceof GestoreCatalogo) {
                //apriremo la view del gestore catalogo
                System.out.println("Benvenuto gestore catalogo " + p.getNome() + " " + p.getCognome() + "!");
                GestoreCatalogo g = (GestoreCatalogo) p;
                System.out.println("email: " + g.getEmailGestore());
               // JOptionPane.showMessageDialog(null,"Benvenuto gestore catalogo " + p.getNome() + " " + p.getCognome() + "!");
                SessionManager.getInstance().getSession().put("gestore", g);
                //qui quella del gestore
                finestra.setVisible(false);
                GestoreFrame gestFrame = new GestoreFrame();
                gestFrame.setVisible(true);
                SessionManager.getInstance().getSession().put("finestra_gestore", gestFrame);

            } else if (p instanceof Utente) {
                // apriremo la view dell'utente
                System.out.println("Benvenuto utente " + p.getNome() + " " + p.getCognome() + "!");
                Utente u = (Utente) p;
                System.out.println("email: " + u.getEmailUtente());
              //  JOptionPane.showMessageDialog(null,"Benvenuto Utente " + p.getNome() + " " + p.getCognome() + "!");
                SessionManager.getInstance().getSession().put("utente", u);
                //qui quella dell'utente
                finestra.setVisible(false);

               // Carrello carrelli = CarrelloBusiness.getInstance().carrelloUtente(u);
                Carrello c = CarrelloBusiness.getInstance().carrelloUtente(u);
              // if(carrelli==null)
               //     CarrelloBusiness.getInstance().inserisciCarrello(u.getEmailUtente());
//
//                Carrello c = new Carrello();
//
//                Iterator i = carrelli.iterator();
//                while (i.hasNext()){
//                    Carrello j = (Carrello)i.next();
//                    if(!j.isUsato())
//                        c=j;
//
//                }



                SessionManager.getInstance().getSession().put("carrello",c);


                UtenteFrame utFr = new UtenteFrame(ProdottoBusiness.getInstance().prodottiPresenti());
                SessionManager.getInstance().getSession().put("finestra_utente", utFr);



            }
        }
        else {

            System.out.println("Il login non è andato a buon fine ...");
            JOptionPane.showMessageDialog(null," LOGIN FAILED ! ");
        }



    }




    }
}
