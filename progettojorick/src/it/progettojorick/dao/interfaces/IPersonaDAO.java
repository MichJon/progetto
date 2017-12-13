package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Persona;

public interface IPersonaDAO extends IBaseDAO<Persona> {

    Persona findUsersByUsernameAndPassword(String email,byte[] password);

    void insertPersona (String email, String nome, String cognome, byte[] password, String indirizzo, String numTelefono);

     Persona findByEmail(String email);

}
