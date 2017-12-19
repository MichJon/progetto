package it.progettojorick.business;

import it.progettojorick.dao.mysql.PreferenzeConsegnaDAO;
import it.progettojorick.model.PreferenzeConsegna;
import it.progettojorick.model.Utente;

import java.util.ArrayList;

public class PreferenzeConsegnaBusiness {
    private static PreferenzeConsegnaBusiness instance;

    public static PreferenzeConsegnaBusiness getInstance(){
        if (instance == null)
            instance = new PreferenzeConsegnaBusiness();
        return instance;
    }

    public PreferenzeConsegna preferenzaConsegna (Utente u) {

            return PreferenzeConsegnaDAO.getInstance().findByUtente(u.getEmailUtente());


    }

    public void inserisciPreferenza(Utente u, String nominativo, String indirizzo){

        PreferenzeConsegnaDAO.getInstance().insertPreferenze(u.getEmailUtente(),nominativo, indirizzo);

    }



}
