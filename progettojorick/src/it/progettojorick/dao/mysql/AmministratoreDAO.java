package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.IAmministratoreDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.Amministratore;

import java.util.ArrayList;
import java.util.Iterator;

public class AmministratoreDAO implements IAmministratoreDAO {

    //singleton design pattern
    private static AmministratoreDAO instance;

    public static AmministratoreDAO getInstance(){
        if (instance==null)
            instance = new AmministratoreDAO();
        return instance;
    }


    @Override
    public Amministratore findByEmail(String email) {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM amministratore WHERE persona_email = '"+email+"';");

        if (risultato.size()==0) return null;

        Amministratore a=new Amministratore();

        String[] riga= risultato.get(0);
        a.setEmailAmministratore(riga[0]);



        return a;
    }

    @Override
    public ArrayList<Amministratore> findAll() {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM amministratore");

        if (risultato.size()==0) return null;
        ArrayList<Amministratore> listaAmministratori = new ArrayList<Amministratore>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            Amministratore a = new Amministratore();
            a.setEmailAmministratore(riga[0]);



            listaAmministratori.add(a);
        }
        return listaAmministratori;
    }
}
