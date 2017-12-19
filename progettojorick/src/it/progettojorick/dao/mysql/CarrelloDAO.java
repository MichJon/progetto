package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.ICarrelloDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.Carrello;
import it.progettojorick.model.Prodotto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class CarrelloDAO implements ICarrelloDAO {

    //singleton design pattern
    private static CarrelloDAO instance;

    public static CarrelloDAO getInstance(){
        if (instance==null)
            instance = new CarrelloDAO();
        return instance;
    }


//    @Override
//    public Carrello findByUtente(String emailutente) {
//        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM carrello WHERE utente_persona_email = '"+emailutente+"';");
//        if (risultato.size()==0) return null;
//
//        Carrello c=new Carrello();
//
//        String[] riga= risultato.get(0);
//        c.setIdcarrello(Integer.parseInt(riga[0]));
//        //c.setEmailutente(riga[1]);
//        c.setUtente(UtenteDAO.getInstance().findByEmail(riga[1]));
//        c.setProdottiContenuti(CarrelloDAO.getInstance().findProdottiContenuti(Integer.parseInt(riga[0])));
//        return c;
//    }

    @Override
    public ArrayList<Carrello> findByUtente(String emailutente) {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM carrello WHERE utente_persona_email = '"+emailutente+"';");
        if (risultato.size()==0) return null;
        ArrayList<Carrello> listaCarrelli = new ArrayList<Carrello>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            Carrello c=new Carrello();
            c.setIdcarrello(Integer.parseInt(riga[0]));
            //c.setEmailutente(riga[1]);
            c.setUtente(UtenteDAO.getInstance().findByEmail(riga[1]));
            c.setProdottiContenuti(CarrelloDAO.getInstance().findProdottiContenuti(Integer.parseInt(riga[0])));
            listaCarrelli.add(c);
        }
        return listaCarrelli;
    }


    @Override
    public ArrayList<Carrello> findAll() {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM carrello");
        if (risultato.size()==0) return null;
        ArrayList<Carrello> listaCarrelli = new ArrayList<Carrello>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            Carrello c=new Carrello();
            c.setIdcarrello(Integer.parseInt(riga[0]));
            //c.setEmailutente(riga[1]);
            c.setUtente(UtenteDAO.getInstance().findByEmail(riga[1]));
            c.setProdottiContenuti(CarrelloDAO.getInstance().findProdottiContenuti(Integer.parseInt(riga[0])));
            listaCarrelli.add(c);
        }
        return listaCarrelli;
    }

    @Override
    public Carrello findById(int id) {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM carrello WHERE idcarrello="+id);
        if (risultato.size()==0) return null;

        Carrello c=new Carrello();

        String[] riga= risultato.get(0);
        c.setIdcarrello(Integer.parseInt(riga[0]));
        //c.setEmailutente(riga[1]);
        c.setUtente(UtenteDAO.getInstance().findByEmail(riga[1]));
        c.setProdottiContenuti(CarrelloDAO.getInstance().findProdottiContenuti(Integer.parseInt(riga[0])));
        return c;
    }

    @Override
    public void insertCarrello( String email) {

       //if (this.findByUtente(email)==null)
        DbConnection.getInstance().eseguiAggiornamento("INSERT INTO carrello (utente_persona_email) " +
                "VALUES ('"+email+"');");

    }

    @Override
    public ArrayList<Prodotto> findProdottiContenuti(int id) {

        ArrayList<String []>  risNomiProdotti= DbConnection.getInstance().eseguiQuery("SELECT prodotto_nome_prodotto FROM carrello_has_prodotto WHERE carrello_idcarrello =" +id );
        ArrayList<Prodotto> prodottiCont = new ArrayList<Prodotto>();
        if (risNomiProdotti.size()!=0) {//return null;
        Iterator<String[]> i = risNomiProdotti.iterator();

        while (i.hasNext()){
            String[] riga = i.next();
            Prodotto ProdottoContenuto = ProdottoDAO.getInstance().findByName(riga[0]);
            prodottiCont.add(ProdottoContenuto);

        }
        }
        return prodottiCont;
    }

    @Override
    public void insertProdottoInCarrello(String nomeProdotto, int idCarrello) {


            DbConnection.getInstance().eseguiAggiornamento("INSERT INTO carrello_has_prodotto (carrello_idcarrello,prodotto_nome_prodotto)" +
                    "VALUES ('" + idCarrello + "','" + nomeProdotto + "');");


    }


}
