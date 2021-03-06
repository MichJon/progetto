package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.IPreferenzeConsegnaDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.PreferenzeConsegna;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.prefs.PreferenceChangeEvent;

public class PreferenzeConsegnaDAO implements IPreferenzeConsegnaDAO {

    //singleton design pattern
    private static PreferenzeConsegnaDAO instance;

    public static PreferenzeConsegnaDAO getInstance(){
        if (instance==null)
            instance = new PreferenzeConsegnaDAO();
        return instance;
    }





    @Override
    public PreferenzeConsegna findByUtente(String email) {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM preferenze_consegna WHERE utente_persona_email ='"+email+"';");
        if (risultato.size()==0) return null;

            String[] riga = risultato.get(0);
            PreferenzeConsegna p = new PreferenzeConsegna();
            p.setUtente(UtenteDAO.getInstance().findByEmail(riga[0]));
            p.setIndirizzoConsegna(riga[1]);
            p.setNominativo(riga[2]);


        return p;
    }

    @Override
    public void insertPreferenze(String email,String nominativo, String indirizzo) {

        if (PreferenzeConsegnaDAO.getInstance().findByUtente(email)==null) {
            DbConnection.getInstance().eseguiAggiornamento("INSERT INTO preferenze_consegna (utente_persona_email,indirizzo_consegna,nominativo) " +
                    "VALUES ('" + email + "','" + indirizzo + "','" + nominativo + "');");
        }
        else {
            DbConnection.getInstance().eseguiAggiornamento("UPDATE  preferenze_consegna SET indirizzo_consegna= '"+indirizzo+"', nominativo = '"+nominativo+"' "+
                    "WHERE utente_persona_email ='"+email+"'");
        }

    }

    @Override
    public ArrayList<PreferenzeConsegna> findAll() {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM preferenze_consegna");
        if (risultato.size()==0) return null;
        ArrayList<PreferenzeConsegna> listaPreferenze = new ArrayList<PreferenzeConsegna>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            PreferenzeConsegna p = new PreferenzeConsegna();
            p.setUtente(UtenteDAO.getInstance().findByEmail(riga[0]));
            p.setIndirizzoConsegna(riga[1]);
            p.setNominativo(riga[2]);
            listaPreferenze.add(p);
        }
        return listaPreferenze;    }
}
