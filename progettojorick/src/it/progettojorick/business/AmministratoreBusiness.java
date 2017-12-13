package it.progettojorick.business;

import it.progettojorick.dao.mysql.RichiestaRegistrazioneDAO;
import it.progettojorick.dao.mysql.UtenteDAO;
import it.progettojorick.model.Persona;
import it.progettojorick.model.RichiestaRegistrazione;

public class AmministratoreBusiness {

    private static AmministratoreBusiness instance;

    public static AmministratoreBusiness getInstance(){
        if (instance == null)
            instance = new AmministratoreBusiness();
        return instance;
    }


    public void confermaRichiesta(String email){

        RichiestaRegistrazione r = RichiestaRegistrazioneDAO.getInstance().findByUtente(email);
        r.setStato("accettata");
        UtenteDAO.getInstance().insertUtente(email);


    }
    public void negaRichiesta(String email){

        RichiestaRegistrazione r = RichiestaRegistrazioneDAO.getInstance().findByUtente(email);
        r.setStato("negata");


    }

}
