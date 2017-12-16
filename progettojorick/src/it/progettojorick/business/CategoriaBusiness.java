package it.progettojorick.business;

import it.progettojorick.dao.mysql.CategoriaDAO;
import it.progettojorick.model.Categoria;
import it.progettojorick.model.GestoreCatalogo;

import java.util.ArrayList;

public class CategoriaBusiness {
    private static CategoriaBusiness instance;

    public static CategoriaBusiness getInstance(){
        if (instance == null)
            instance = new CategoriaBusiness();
        return instance;
    }
    public Categoria creaCategoria (String nome, GestoreCatalogo g){

        Categoria c = new Categoria();

        c.setNomecategoria(nome);
        c.setGestoreCatalogo(g);

        return c;
    }


    public void inserisciCategoria(Categoria c){

        if (CategoriaDAO.getInstance().findByName(c.getNomecategoria())==null)
            CategoriaDAO.getInstance().insertCategoria(c);

    }

    public ArrayList<Categoria> categoriePresenti(){

        return  CategoriaDAO.getInstance().findAll();

    }

}
