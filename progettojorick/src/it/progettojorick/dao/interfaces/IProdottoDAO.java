package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Prodotto;

public interface IProdottoDAO extends IBaseDAO<Prodotto> {

    Prodotto findByName(String nome);

    void insertProdotto(Prodotto p);
}
