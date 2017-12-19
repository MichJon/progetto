package it.progettojorick.dao.interfaces;

import it.progettojorick.model.RichiestaOrdine;

public interface IRichiestaOrdineDAO extends IBaseDAO<RichiestaOrdine> {

    RichiestaOrdine findById (int id);
    void insertRichiestaOrdine(String stato, int idCarrello, String emailUtente, long numCarta);
    void setStato(String stato, RichiestaOrdine r);

}
