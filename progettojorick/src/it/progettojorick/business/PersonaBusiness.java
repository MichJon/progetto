package it.progettojorick.business;

import it.progettojorick.dao.mysql.PersonaDAO;
import it.progettojorick.dao.mysql.UtenteDAO;
import it.progettojorick.model.Persona;

public class PersonaBusiness {

    private static PersonaBusiness instance;

    public static PersonaBusiness getInstance(){
        if (instance == null)
            instance = new PersonaBusiness();
        return instance;
    }

    public Persona login(String email, byte[] password){

        Persona p = PersonaDAO.getInstance().findUsersByUsernameAndPassword(email,password);
        return p;


    }

//    public void inviaRichiestaRegistrazione(String email, String nome, String cognome, byte[] password, String indirizzo, String numTelefono){
//
//        PersonaDAO.getInstance().insertPersona(email, nome, cognome, password, indirizzo, numTelefono);
//    }

}
