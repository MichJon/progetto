package it.progettojorick.actionListeners;

import it.progettojorick.business.RichiestaRegistrazioneBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.view.LoginFrame;
import it.progettojorick.view.RegistrazioneFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrazioneListener implements ActionListener {

    private RegistrazioneFrame finestra;

    public RegistrazioneListener(RegistrazioneFrame finestra) {
        this.finestra = finestra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //CONTROLLI
        System.out.println("Evento catturato!");
        String nome = finestra.getTxtNome().getText();
        String cognome = finestra.getTxtCognome().getText();
        String email = finestra.getTxtEmail().getText();
        byte[] password = finestra.getTxtPassword().getText().getBytes();
        String indirizzo = finestra.getTxtIndirizzo().getText();
        String numTelefono = finestra.getTxtNumTelefono().getText();


        if (e.getSource() instanceof JButton) {
            if (RichiestaRegistrazioneBusiness.getInstance().controllaPresenza(email) == false) {
                if (!nome.equals("") && !cognome.equals("") && !email.equals("") && password.length > 0 && !indirizzo.equals("") && !numTelefono.equals("")) {
                    if (email.matches("(.*)@(.*)")) {

                        RichiestaRegistrazioneBusiness.getInstance().registraPersona(email, nome, cognome, password, indirizzo, numTelefono);
                        JOptionPane.showMessageDialog(null, "La registrazione è in attesa di conferma.");
                        finestra.setVisible(false);
                        LoginFrame finestraLogin = new LoginFrame();
                        SessionManager.getInstance().getSession().put("finestra_login", finestraLogin);
                    } else {
                        JOptionPane.showMessageDialog(null, "Email inserita non valida.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Riempire tutti i campi.");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Utente già registrato");
            }
        }
    }
}
