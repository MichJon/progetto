package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Carrello;

public interface ICarrelloDAO extends IBaseDAO<Carrello> {

    Carrello findByUtente (String emailutente);
    Carrello findById (int id);

}
