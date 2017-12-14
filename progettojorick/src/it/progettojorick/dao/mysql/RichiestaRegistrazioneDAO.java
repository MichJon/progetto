package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.IRichiestaRegistrazioneDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.Amministratore;
import it.progettojorick.model.Persona;
import it.progettojorick.model.RichiestaRegistrazione;
import it.progettojorick.model.Utente;

import java.util.ArrayList;
import java.util.Iterator;

public class RichiestaRegistrazioneDAO implements IRichiestaRegistrazioneDAO {

    //singleton design pattern
    private static RichiestaRegistrazioneDAO instance;

    public static RichiestaRegistrazioneDAO getInstance(){
        if (instance==null)
            instance = new RichiestaRegistrazioneDAO();
        return instance;
    }


    @Override
    public ArrayList<RichiestaRegistrazione> findAll() {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM richiesta_registrazione");
        if (risultato.size()==0) return null;
        ArrayList<RichiestaRegistrazione> listaRichieste = new ArrayList<RichiestaRegistrazione>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            RichiestaRegistrazione r = new RichiestaRegistrazione();
            r.setIdRegistrazione(Integer.parseInt(riga[0]));
            r.setAmministratore(AmministratoreDAO.getInstance().findByEmail(riga[1]));
            r.setPersona(PersonaDAO.getInstance().findByEmail(riga[2]));
            r.setStato(riga[3]);
            listaRichieste.add(r);
        }
        return listaRichieste;

    }

    @Override
    public void setStato(String stato, RichiestaRegistrazione r) {

        DbConnection.getInstance().eseguiAggiornamento("UPDATE richiesta_registrazione SET stato_registrazione = '"+stato+"' " +
                "WHERE persona_email = '"+r.getPersona().getEmail()+"'");
    }

    public void setAmministratore(Amministratore a, RichiestaRegistrazione r) {

        DbConnection.getInstance().eseguiAggiornamento("UPDATE richiesta_registrazione SET amministratore_persona_email = '"+a.getEmailAmministratore()+"' " +
                "WHERE persona_email = '"+r.getPersona().getEmail()+"'");
    }


    @Override
    public RichiestaRegistrazione findByUtente(String email) {

            ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM richiesta_registrazione WHERE persona_email = '"+email+"';");

            if (risultato.size()==0) return null;

            RichiestaRegistrazione r=new RichiestaRegistrazione();

            String[] riga= risultato.get(0);
            r.setIdRegistrazione(Integer.parseInt(riga[0]));
            r.setAmministratore(AmministratoreDAO.getInstance().findByEmail(riga[1]));
            r.setPersona(PersonaDAO.getInstance().findByEmail(riga[2]));
            r.setStato(riga[3]);


            return r;


    }


    @Override
    public void insertRichiesta(RichiestaRegistrazione r) {
        DbConnection.getInstance().eseguiAggiornamento("INSERT INTO richiesta_registrazione (persona_email,stato_registrazione)"+
                " VALUES ('"+ r.getPersona().getEmail() + "', '" + r.getStato() +"');");
    }

    @Override
    public void deleteRichiesta(RichiestaRegistrazione r) {
        DbConnection.getInstance().eseguiAggiornamento("DELETE FROM richiesta_registrazione WHERE persona_email='"+r.getPersona().getEmail()+"';");
    }


}
