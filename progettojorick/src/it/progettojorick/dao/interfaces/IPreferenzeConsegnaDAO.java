package it.progettojorick.dao.interfaces;

import it.progettojorick.model.PreferenzeConsegna;

import java.util.ArrayList;

public interface IPreferenzeConsegnaDAO extends IBaseDAO<PreferenzeConsegna> {

    PreferenzeConsegna findByUtente (String email);
    void insertPreferenze(String email,String nominativo, String indirizzo);
}
