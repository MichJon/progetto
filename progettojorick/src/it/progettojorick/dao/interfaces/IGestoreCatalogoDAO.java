package it.progettojorick.dao.interfaces;

import it.progettojorick.model.GestoreCatalogo;

public interface IGestoreCatalogoDAO extends IBaseDAO<GestoreCatalogo> {

    GestoreCatalogo findByEmail(String email);
}
