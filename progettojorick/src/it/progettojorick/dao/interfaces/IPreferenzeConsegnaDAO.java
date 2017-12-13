package it.progettojorick.dao.interfaces;

import it.progettojorick.model.PreferenzeConsegna;

public interface IPreferenzeConsegnaDAO extends IBaseDAO<PreferenzeConsegna> {

    PreferenzeConsegna findByUtente (String email);
}
