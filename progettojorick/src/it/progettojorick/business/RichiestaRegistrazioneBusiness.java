package it.progettojorick.business;

import it.progettojorick.dao.mysql.PersonaDAO;
import it.progettojorick.dao.mysql.RichiestaRegistrazioneDAO;
import it.progettojorick.model.Persona;
import it.progettojorick.model.RichiestaRegistrazione;

public class RichiestaRegistrazioneBusiness {

    private static RichiestaRegistrazioneBusiness instance;

    public static RichiestaRegistrazioneBusiness getInstance(){
        if (instance == null)
            instance = new RichiestaRegistrazioneBusiness();
        return instance;
    }

    public void registraPersona(String email, String nome, String cognome, byte[] password, String indirizzo, String numTelefono){

        PersonaDAO.getInstance().insertPersona(email, nome, cognome, password, indirizzo, numTelefono);
        Persona p = PersonaDAO.getInstance().findByEmail(email);
        RichiestaRegistrazione r = new RichiestaRegistrazione();
        r.setPersona(p);
        RichiestaRegistrazioneDAO.getInstance().insertRichiesta(r);

    }

}
