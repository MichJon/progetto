package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Utente;

public interface IUtenteDAO extends IBaseDAO<Utente> {

    Utente findByEmail(String email);
}
