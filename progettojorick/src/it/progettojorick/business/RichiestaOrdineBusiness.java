package it.progettojorick.business;

import it.progettojorick.dao.mysql.RichiestaOrdineDAO;
import it.progettojorick.model.RichiestaOrdine;

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

}
