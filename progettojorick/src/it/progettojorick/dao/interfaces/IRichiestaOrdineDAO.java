package it.progettojorick.dao.interfaces;

import it.progettojorick.model.RichiestaOrdine;

public interface IRichiestaOrdineDAO extends IBaseDAO<RichiestaOrdine> {

    RichiestaOrdine findById (int id);


}
