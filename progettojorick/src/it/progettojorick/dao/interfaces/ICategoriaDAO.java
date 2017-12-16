package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Categoria;

public interface ICategoriaDAO extends IBaseDAO<Categoria> {

    Categoria findByName(String nome);
    void insertCategoria (Categoria c);
}
