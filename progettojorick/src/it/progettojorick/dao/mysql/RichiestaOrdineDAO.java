package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.IRichiestaOrdineDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.Amministratore;
import it.progettojorick.model.RichiestaOrdine;
import java.util.ArrayList;
import java.util.Iterator;

public class RichiestaOrdineDAO implements IRichiestaOrdineDAO {

    //singleton design pattern
    private static RichiestaOrdineDAO instance;

    public static RichiestaOrdineDAO getInstance(){
        if (instance==null)
            instance = new RichiestaOrdineDAO();
        return instance;
    }

    @Override
    public RichiestaOrdine findById(int id) {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM richiesta_ordine WHERE idrichiesta_ordine="+id);
        if (risultato.size()==0) return null;

        RichiestaOrdine r=new RichiestaOrdine();

        String[] riga= risultato.get(0);
        r.setIdRichiesta(Integer.parseInt(riga[0]));
        r.setAmministratore(AmministratoreDAO.getInstance().findByEmail(riga[1]));
        r.setStato(riga[2]);
        r.setCarrello(CarrelloDAO.getInstance().findById(Integer.parseInt(riga[3])));
        r.setUtente(UtenteDAO.getInstance().findByEmail(riga[4]));
        r.setPagamento(PagamentoDAO.getInstance().findByNumcarta(Integer.parseInt(riga[5])));

        return r;
    }

    @Override
    public ArrayList<RichiestaOrdine> findAll() {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM richiesta_ordine");
        if (risultato.size()==0) return null;
        ArrayList<RichiestaOrdine> listaRichieste = new ArrayList<RichiestaOrdine>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            RichiestaOrdine r = new RichiestaOrdine();
            r.setIdRichiesta(Integer.parseInt(riga[0]));
            r.setAmministratore(AmministratoreDAO.getInstance().findByEmail(riga[1]));
            r.setStato(riga[2]);
            r.setCarrello(CarrelloDAO.getInstance().findById(Integer.parseInt(riga[3])));
            r.setUtente(UtenteDAO.getInstance().findByEmail(riga[4]));
            r.setPagamento(PagamentoDAO.getInstance().findByNumcarta(Integer.parseInt(riga[5])));
            listaRichieste.add(r);
        }
        return listaRichieste;
    }

    public ArrayList<RichiestaOrdine> findByUtente(String email) {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM richiesta_ordine WHERE utente_persona_email = '"+email+"';");
        if (risultato.size()==0) return null;
        ArrayList<RichiestaOrdine> listaRichieste = new ArrayList<RichiestaOrdine>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            RichiestaOrdine r = new RichiestaOrdine();
            r.setIdRichiesta(Integer.parseInt(riga[0]));
            r.setAmministratore(AmministratoreDAO.getInstance().findByEmail(riga[1]));
            r.setStato(riga[2]);
            r.setCarrello(CarrelloDAO.getInstance().findById(Integer.parseInt(riga[3])));
            r.setUtente(UtenteDAO.getInstance().findByEmail(riga[4]));
            r.setPagamento(PagamentoDAO.getInstance().findByNumcarta(Integer.parseInt(riga[5])));
            listaRichieste.add(r);
        }
        return listaRichieste;

    }
}
