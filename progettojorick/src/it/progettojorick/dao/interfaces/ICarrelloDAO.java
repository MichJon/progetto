package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Carrello;
import it.progettojorick.model.Prodotto;

import java.util.ArrayList;

public interface ICarrelloDAO extends IBaseDAO<Carrello> {

    Carrello findByUtente (String emailutente);
    Carrello findById (int id);
    void insertCarrello(String email);
    ArrayList<Prodotto> findProdottiContenuti(int id);
    void insertProdottoInCarrello(String nomeProdotto, int idCarrello);
}
