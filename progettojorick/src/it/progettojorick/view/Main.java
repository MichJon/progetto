package it.progettojorick.view;

import it.progettojorick.business.PersonaBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Amministratore;
import it.progettojorick.model.GestoreCatalogo;
import it.progettojorick.model.Persona;
import it.progettojorick.model.Utente;

public class Main {
    public static void main(String args[]) {


//      new LoginFrame();

        LoginFrame loginFrame = new LoginFrame();
        SessionManager.getInstance().getSession().put("finestra_login", loginFrame);

//      new LoginFrame();
//      new RegistrazioneFrame();
//>>>>>>> origin/master

    }
}
