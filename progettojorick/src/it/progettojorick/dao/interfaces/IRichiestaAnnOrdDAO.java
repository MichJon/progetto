package it.progettojorick.dao.interfaces;

import it.progettojorick.model.RichiestaAnnOrd;

import java.util.ArrayList;

public interface IRichiestaAnnOrdDAO extends IBaseDAO<RichiestaAnnOrd> {

    ArrayList<RichiestaAnnOrd> findByUtente (String email);
}
