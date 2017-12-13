package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.IDistributoreDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.Distributore;

import java.util.ArrayList;
import java.util.Iterator;

public class DistributoreDAO implements IDistributoreDAO {

    //singleton design pattern
    private static DistributoreDAO instance;

    public static DistributoreDAO getInstance(){
        if (instance==null)
            instance = new DistributoreDAO();
        return instance;
    }


    @Override
    public Distributore findById(int id){
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM distributore WHERE iddistributore="+id);
        if (risultato.size()==0) return null;

        Distributore d=new Distributore();

        String[] riga= risultato.get(0);
        d.setId(Integer.parseInt(riga[0]));
        d.setNome(riga[1]);
        d.setDescrizione(riga[2]);

        return d;
    }

    @Override
    public ArrayList<Distributore> findAll() {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM distributore");
        if (risultato.size()==0) return null;
        ArrayList<Distributore> listaDistributori = new ArrayList<Distributore>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            Distributore d = new Distributore();
            d.setId(Integer.parseInt(riga[0]));
            d.setNome(riga[1]);
            d.setDescrizione(riga[2]);
            listaDistributori.add(d);
        }
        return listaDistributori;
    }

}
