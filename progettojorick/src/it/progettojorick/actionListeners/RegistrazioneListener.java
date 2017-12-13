package it.progettojorick.actionListeners;

import it.progettojorick.view.LoginFrame;
import it.progettojorick.view.RegistrazioneFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrazioneListener implements ActionListener {

    private RegistrazioneFrame finestra;

    public RegistrazioneListener(RegistrazioneFrame finestra){
        this.finestra=finestra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //CONTROLLI
        System.out.println("Evento catturato!");
        if ("RANDOM_MENU_ITEM".equals(e.getActionCommand())){
            JOptionPane.showMessageDialog(null,"Hai premuto la voce di menu random");

        }






    }
}
