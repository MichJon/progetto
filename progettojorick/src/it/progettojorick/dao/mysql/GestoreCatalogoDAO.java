package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.IGestoreCatalogoDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.GestoreCatalogo;

import java.util.ArrayList;
import java.util.Iterator;

public class GestoreCatalogoDAO implements IGestoreCatalogoDAO {
    //singleton design pattern
    private static GestoreCatalogoDAO instance;

    public static GestoreCatalogoDAO getInstance(){
        if (instance==null)
            instance = new GestoreCatalogoDAO();
        return instance;
    }


    @Override
    public GestoreCatalogo findByEmail(String email) {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM gestore_catalogo WHERE persona_email = '"+email+"';");

        if (risultato.size()==0) return null;

        GestoreCatalogo g=new GestoreCatalogo();

        String[] riga= risultato.get(0);
        g.setEmailGestore(riga[0]);



        return g;
    }

    @Override
    public ArrayList<GestoreCatalogo> findAll() {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM gestore_catalogo");

        if (risultato.size()==0) return null;
        ArrayList<GestoreCatalogo> listaGestori = new ArrayList<GestoreCatalogo>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            GestoreCatalogo g = new GestoreCatalogo();
            g.setEmailGestore(riga[0]);



            listaGestori.add(g);
        }
        return listaGestori;
    }
}
