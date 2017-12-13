package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Produttore;

public interface IProduttoreDAO extends IBaseDAO<Produttore> {

    Produttore findById(int id);
}
