package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.IRichiestaAnnOrdDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.RichiestaAnnOrd;
import it.progettojorick.model.Utente;

import java.util.ArrayList;
import java.util.Iterator;

public class RichiestaAnnOrdDAO implements IRichiestaAnnOrdDAO {

    //singleton design pattern
    private static RichiestaAnnOrdDAO instance;

    public static RichiestaAnnOrdDAO getInstance(){
        if (instance==null)
            instance = new RichiestaAnnOrdDAO();
        return instance;
    }


    @Override
    public ArrayList<RichiestaAnnOrd> findByUtente(String email) {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM richiesta_annullamento_ordine WHERE utente_persona_email = '"+email+"';");
        if (risultato.size()==0) return null;
        ArrayList<RichiestaAnnOrd> listaRichieste = new ArrayList<RichiestaAnnOrd>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            RichiestaAnnOrd r = new RichiestaAnnOrd();
            r.setId(Integer.parseInt(riga[0]));
            r.setAmministratore(AmministratoreDAO.getInstance().findByEmail(riga[1]));
            r.setUtente(UtenteDAO.getInstance().findByEmail(riga[2]));
            r.setRichiestaDaAnnullare(RichiestaOrdineDAO.getInstance().findById(Integer.parseInt(riga[3])));
            r.setStato(riga[4]);
            listaRichieste.add(r);
        }
        return listaRichieste;
    }

    @Override
    public ArrayList<RichiestaAnnOrd> findAll() {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM richiesta_annullamento_ordine");

        if (risultato.size()==0) return null;

        ArrayList<RichiestaAnnOrd> listaRichieste = new ArrayList<RichiestaAnnOrd>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            RichiestaAnnOrd r = new RichiestaAnnOrd();
            r.setId(Integer.parseInt(riga[0]));
            r.setAmministratore(AmministratoreDAO.getInstance().findByEmail(riga[1]));
            r.setUtente(UtenteDAO.getInstance().findByEmail(riga[2]));
            r.setRichiestaDaAnnullare(RichiestaOrdineDAO.getInstance().findById(Integer.parseInt(riga[3])));
            r.setStato(riga[4]);
            listaRichieste.add(r);
        }
        return listaRichieste;
    }
}
