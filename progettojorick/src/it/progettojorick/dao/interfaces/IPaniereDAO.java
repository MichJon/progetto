package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Paniere;

import java.util.ArrayList;

public interface IPaniereDAO extends IBaseDAO<Paniere> {

    ArrayList<Paniere> findByEmailutente( String emailutente);

    Paniere findById( int id);
}
