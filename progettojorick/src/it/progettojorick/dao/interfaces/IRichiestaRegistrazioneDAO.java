package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Amministratore;
import it.progettojorick.model.Persona;
import it.progettojorick.model.RichiestaRegistrazione;

public interface IRichiestaRegistrazioneDAO extends IBaseDAO<RichiestaRegistrazione> {


    void setStato(String stato, RichiestaRegistrazione r);

    RichiestaRegistrazione findByUtente(String email);

    void insertRichiesta(RichiestaRegistrazione r);

    void deleteRichiesta(RichiestaRegistrazione r);
}
