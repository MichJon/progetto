package it.progettojorick.business;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import it.progettojorick.dao.mysql.CarrelloDAO;
import it.progettojorick.model.Carrello;
import it.progettojorick.model.Prodotto;
import it.progettojorick.model.Utente;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class CarrelloBusiness {

    private static CarrelloBusiness instance;

    public static CarrelloBusiness getInstance(){
        if (instance == null)
            instance = new CarrelloBusiness();
        return instance;
    }

    public Carrello carrelloUtente(Utente u){

        ArrayList carrelli = CarrelloBusiness.getInstance().carrelliUtente(u);
        return (Carrello)carrelli.get(carrelli.size()-1);


    }


    public ArrayList<Carrello> carrelliUtente(Utente u){

        return CarrelloDAO.getInstance().findByUtente(u.getEmailUtente());

    }

    public void inserisciCarrello(String email){
        CarrelloDAO.getInstance().insertCarrello(email);
    }

    public ArrayList<Prodotto> prodottiContenuti (Carrello c){

        return CarrelloDAO.getInstance().findProdottiContenuti(c.getIdcarrello());

    }

    public void inserisciProdottoNelCarrello (Prodotto p, Carrello c)  {

        CarrelloDAO.getInstance().insertProdottoInCarrello(p.getNome(),c.getIdcarrello());
        c.addProdottoContenuto(p);
    }

    public boolean isPresente(Prodotto p, Carrello c){

      ArrayList<Prodotto> prodottiContenuti = c.getProdottiContenuti();
        Iterator i = prodottiContenuti.iterator();

        while (i.hasNext()){

            Prodotto prod = (Prodotto)i.next();

            if(prod.getNome().equals(p.getNome())) return true;
        }

        return false;
    }

    public Carrello trovaCarrello(int id){

        return CarrelloDAO.getInstance().findById(id);

    }
}
