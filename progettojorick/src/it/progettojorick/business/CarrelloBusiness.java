package it.progettojorick.business;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import it.progettojorick.dao.mysql.CarrelloDAO;
import it.progettojorick.model.Carrello;
import it.progettojorick.model.Prodotto;
import it.progettojorick.model.Utente;

import javax.swing.*;
import java.util.ArrayList;

public class CarrelloBusiness {

    private static CarrelloBusiness instance;

    public static CarrelloBusiness getInstance(){
        if (instance == null)
            instance = new CarrelloBusiness();
        return instance;
    }

    public Carrello carrelloUtente(Utente u){

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
    }

}
