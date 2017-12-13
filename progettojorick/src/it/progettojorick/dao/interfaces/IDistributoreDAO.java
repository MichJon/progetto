package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Distributore;

public interface IDistributoreDAO extends IBaseDAO<Distributore> {

    Distributore findById(int id);
}
