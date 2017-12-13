package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Persona;

public interface IPersonaDAO extends IBaseDAO<Persona> {

    Persona findUsersByUsernameAndPassword(String email,byte[] password);

}
