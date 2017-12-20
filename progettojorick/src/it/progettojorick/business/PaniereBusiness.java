package it.progettojorick.business;

import it.progettojorick.dao.mysql.PaniereDAO;
import it.progettojorick.model.Paniere;
import it.progettojorick.model.Prodotto;
import it.progettojorick.model.Utente;

import java.util.ArrayList;

public class PaniereBusiness {

    private static PaniereBusiness instance;

    public static PaniereBusiness getInstance(){
        if (instance == null)
            instance = new PaniereBusiness();
        return instance;
    }


    public ArrayList<Paniere> trovaPanieriUtente(Utente u){

        return PaniereDAO.getInstance().findByEmailutente(u.getEmailUtente());

    }

    public ArrayList<Prodotto> prodottiContenuti(Paniere p){

        return PaniereDAO.getInstance().prodottiContenuti(p.getIdpaniere());

    }

    public void inserisciProdottoNelPaniere(String nomeProdotto, Paniere pan){

        PaniereDAO.getInstance().insertProdottoInPaniere(pan.getIdpaniere(), nomeProdotto);
        Paniere p= PaniereDAO.getInstance().findById(pan.getIdpaniere());

        SessionManager.getInstance().getSession().put("paniere",p);

    }

    public void cancellaProdottoDalPaniere(String nomeProdotto, Paniere pan){

        PaniereDAO.getInstance().deleteProdottoFromPaniere(nomeProdotto, pan.getIdpaniere());
        //PaniereDAO.getInstance().
    }


    public void inserisciPaniere(String nome, Utente u){

        PaniereDAO.getInstance().insertPaniere(nome, u.getEmailUtente());

    }


}
