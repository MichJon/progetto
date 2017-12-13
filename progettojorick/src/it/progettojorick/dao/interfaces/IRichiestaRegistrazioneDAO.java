package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Amministratore;
import it.progettojorick.model.Persona;
import it.progettojorick.model.RichiestaRegistrazione;

public interface IRichiestaRegistrazioneDAO extends IBaseDAO {


    void setStato(String stato, Persona p);

    RichiestaRegistrazione findByUtente(String email);

    void insertRichiesta(Persona p, String stato);
}
