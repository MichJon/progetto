package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.IProduttoreDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.Produttore;

import java.util.ArrayList;
import java.util.Iterator;

public class ProduttoreDAO implements IProduttoreDAO {

    //singleton design pattern
    private static ProduttoreDAO instance;


    public static ProduttoreDAO getInstance(){
        if (instance==null)
            instance = new ProduttoreDAO();
        return instance;
    }
    @Override
    public Produttore findById(int id){
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM produttore WHERE idproduttore="+id);
        if (risultato.size()==0) return null;

        Produttore p=new Produttore();

        String[] riga= risultato.get(0);
        p.setId(Integer.parseInt(riga[0]));
        p.setNome(riga[1]);
        p.setDescrizione(riga[2]);

        return p;
    }

    @Override
    public ArrayList<Produttore> findAll() {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM produttore");
        if (risultato.size()==0) return null;
        ArrayList<Produttore> listaProduttori = new ArrayList<Produttore>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            Produttore p = new Produttore();
            p.setId(Integer.parseInt(riga[0]));
            p.setNome(riga[1]);
            p.setDescrizione(riga[2]);
            listaProduttori.add(p);
        }
        return listaProduttori;
    }
}
