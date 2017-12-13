package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Amministratore;

public interface IAmministratoreDAO extends IBaseDAO<Amministratore> {

    Amministratore findByEmail(String email);

}
