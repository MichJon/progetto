package it.progettojorick.business;

import it.progettojorick.dao.mysql.RichiestaOrdineDAO;
import it.progettojorick.model.RichiestaOrdine;

import it.progettojorick.model.Utente;


import java.util.ArrayList;

public class RichiestaOrdineBusiness {

    private static RichiestaOrdineBusiness instance;

    public static RichiestaOrdineBusiness getInstance(){
        if (instance == null)
            instance = new RichiestaOrdineBusiness();
        return instance;
    }


    public ArrayList<RichiestaOrdine> richiestePresenti(){
        return RichiestaOrdineDAO.getInstance().findAll();

    }


    public void inviaRichiestaOrdine(String stato, int idCarrello, String emailUt, long numCarta){


        RichiestaOrdineDAO.getInstance().insertRichiestaOrdine(stato,idCarrello,emailUt,numCarta);

    }

    public ArrayList<RichiestaOrdine> richiesteOrdineUtente(Utente u){

        return RichiestaOrdineDAO.getInstance().findByUtente(u.getEmailUtente());

    }

    public RichiestaOrdine trovaRichiesta(int id){

        return RichiestaOrdineDAO.getInstance().findById(id);

    }


  //  boolean carrelloUsato(c)


}
