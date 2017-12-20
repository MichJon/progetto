package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.ICategoriaDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.Categoria;
import it.progettojorick.model.GestoreCatalogo;

import java.util.ArrayList;
import java.util.Iterator;






public class CategoriaDAO implements ICategoriaDAO {

    private static CategoriaDAO instance;

    public static CategoriaDAO getInstance(){
        if (instance==null)
            instance = new CategoriaDAO();
        return instance;
    }

    @Override
    public Categoria findByName(String nome) {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM categoria WHERE nome_categoria = '"+nome+"';");
        if (risultato.size()==0) return null;

        Categoria c=new Categoria();

        String[] riga= risultato.get(0);
        c.setNomecategoria(riga[0]);
        c.setGestoreCatalogo(GestoreCatalogoDAO.getInstance().findByEmail(riga[1]));

        return c;
    }

    @Override
    public ArrayList<Categoria> findAll() {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM categoria");
        if (risultato.size()==0) return null;
        ArrayList<Categoria> listaCategorie = new ArrayList<Categoria>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            Categoria c=new Categoria();
            c.setNomecategoria(riga[0]);
            c.setGestoreCatalogo(GestoreCatalogoDAO.getInstance().findByEmail(riga[1]));
            listaCategorie.add(c);
        }
        return listaCategorie;
    }
    @Override
    public void insertCategoria(Categoria c) {

        DbConnection.getInstance().eseguiAggiornamento("INSERT INTO categoria (nome_categoria,gestore_catalogo_persona_email)" +
                " VALUES ('"+c.getNomecategoria()+"','"+c.getGestoreCatalogo().getEmailGestore()+"');");


    }


    public void deleteCategoria(String nome){

        DbConnection.getInstance().eseguiAggiornamento("DELETE FROM categoria WHERE nome_categoria='"+nome+"';");

    }
}
