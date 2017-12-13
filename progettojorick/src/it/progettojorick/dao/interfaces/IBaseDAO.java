package it.progettojorick.dao.interfaces;

import java.util.ArrayList;

public interface IBaseDAO<T> {

    //findById
 //   T findById(int id);
                                        // tipizzazione
    //findAll
    ArrayList<T> findAll();

    //findByEmail
  //  T findByEmail(String email);
}
