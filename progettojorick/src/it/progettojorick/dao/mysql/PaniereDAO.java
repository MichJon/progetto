package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.IPaniereDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.Paniere;
import it.progettojorick.model.Prodotto;

import java.util.ArrayList;
import java.util.Iterator;

public class PaniereDAO implements IPaniereDAO {

    //singleton design pattern
    private static PaniereDAO instance;

    public static PaniereDAO getInstance(){
        if (instance==null)
            instance = new PaniereDAO();
        return instance;
    }

    @Override
    public ArrayList<Paniere> findByEmailutente(String emailutente) {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM paniere WHERE utente_persona_email = '" +emailutente+"';");
        if (risultato.size()==0) return null;

        ArrayList<Paniere> listaPanieri = new ArrayList<Paniere>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            Paniere p=new Paniere();
            p.setIdpaniere(Integer.parseInt(riga[0]));
            p.setNome(riga[1]);
           /* p.setEmailutente(riga[2]);
            p.setIdcarrello(Integer.parseInt(riga[3]));*/
            p.setUtente(UtenteDAO.getInstance().findByEmail(riga[2]));
//            p.setCarrello(CarrelloDAO.getInstance().findById(Integer.parseInt(riga[3])));
            p.setProdotti(PaniereDAO.getInstance().prodottiContenuti(Integer.parseInt(riga[0])));
            listaPanieri.add(p);
        }
        return listaPanieri;
    }

    @Override
    public Paniere findById(int id) {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM paniere WHERE idpaniere="+id);
        if (risultato.size()==0) return null;

        Paniere p=new Paniere();

        String[] riga= risultato.get(0);
        p.setIdpaniere(Integer.parseInt(riga[0]));
        p.setNome(riga[1]);
           /* p.setEmailutente(riga[2]);
            p.setIdcarrello(Integer.parseInt(riga[3]));*/
        p.setUtente(UtenteDAO.getInstance().findByEmail(riga[2]));
//        p.setCarrello(CarrelloDAO.getInstance().findById(Integer.parseInt(riga[3])));
        p.setProdotti(PaniereDAO.getInstance().prodottiContenuti(Integer.parseInt(riga[0])));

        return p;



    }

    @Override
    public ArrayList<Paniere> findAll() {

        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM paniere" );
        if (risultato.size()==0) return null;
        ArrayList<Paniere> listaPanieri = new ArrayList<Paniere>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            Paniere p=new Paniere();
            p.setIdpaniere(Integer.parseInt(riga[0]));
            p.setNome(riga[1]);
           /* p.setEmailutente(riga[2]);
            p.setIdcarrello(Integer.parseInt(riga[3]));*/
            p.setUtente(UtenteDAO.getInstance().findByEmail(riga[2]));
            p.setCarrello(CarrelloDAO.getInstance().findById(Integer.parseInt(riga[3])));
            p.setProdotti(PaniereDAO.getInstance().prodottiContenuti(Integer.parseInt(riga[0])));
            listaPanieri.add(p);
        }
    return listaPanieri;
    }


    public ArrayList<Prodotto> prodottiContenuti ( int id){

        ArrayList<String []>  risNomiProdotti= DbConnection.getInstance().eseguiQuery("SELECT prodotto_nome_prodotto FROM paniere_has_prodotto WHERE paniere_idpaniere =" +id );
        ArrayList<Prodotto> prodottiCont = new ArrayList<Prodotto>();
        if (risNomiProdotti.size()==0) return null;
        Iterator<String[]> i = risNomiProdotti.iterator();

        while (i.hasNext()){
            String[] riga = i.next();
            Prodotto ProdottoContenuto = ProdottoDAO.getInstance().findByName(riga[0]);
            prodottiCont.add(ProdottoContenuto);
        }
    return prodottiCont;
    }

    public void insertProdottoInPaniere(int id, String nome){

        DbConnection.getInstance().eseguiAggiornamento("INSERT INTO paniere_has_prodotto (paniere_idpaniere, prodotto_nome_prodotto) " +
                "VALUES ("+id+",'"+nome+"');");

    }

    public void deleteProdottoFromPaniere(String nome, int id){

        DbConnection.getInstance().eseguiAggiornamento("DELETE FROM paniere_has_prodotto WHERE paniere_idpaniere ="+id+" AND " +
                "prodotto_nome_prodotto='"+nome+"';");

    }

    public void insertPaniere(String nome, String emailUt){

        DbConnection.getInstance().eseguiAggiornamento("INSERT INTO paniere (nome_paniere, utente_persona_email) " +
                "VALUES ('"+nome+"','"+emailUt+"');");

    }

    public void deletePaniere(int id){

        DbConnection.getInstance().eseguiAggiornamento("DELETE FROM paniere_has_prodotto WHERE paniere_idpaniere ="+id);
        DbConnection.getInstance().eseguiAggiornamento("DELETE FROM paniere WHERE idpaniere ="+id);

    }
}
