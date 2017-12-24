package it.progettojorick.business;

import it.progettojorick.dao.mysql.PersonaDAO;
import it.progettojorick.dao.mysql.RichiestaOrdineDAO;
import it.progettojorick.dao.mysql.RichiestaRegistrazioneDAO;
import it.progettojorick.dao.mysql.UtenteDAO;
import it.progettojorick.model.Amministratore;
import it.progettojorick.model.Persona;
import it.progettojorick.model.RichiestaOrdine;
import it.progettojorick.model.RichiestaRegistrazione;

public class AmministratoreBusiness {

    private static AmministratoreBusiness instance;

    public static AmministratoreBusiness getInstance(){
        if (instance == null)
            instance = new AmministratoreBusiness();
        return instance;
    }


    public void confermaRichiesta(String email){

        Amministratore a = (Amministratore) SessionManager.getInstance().getSession().get("amministratore");

        RichiestaRegistrazione r = RichiestaRegistrazioneDAO.getInstance().findByUtente(email);
        if (UtenteDAO.getInstance().findByEmail(email)==null) {

            RichiestaRegistrazioneDAO.getInstance().setStato("accettata", r);
            RichiestaRegistrazioneDAO.getInstance().setAmministratore(a, r);

            UtenteDAO.getInstance().insertUtente(email);
        }

    }
    public void negaRichiesta(String email){

        RichiestaRegistrazione r = RichiestaRegistrazioneDAO.getInstance().findByUtente(email);
        if (!r.getStato().equals("accettata")) {
            RichiestaRegistrazioneDAO.getInstance().deleteRichiesta(r);
            Persona p = PersonaDAO.getInstance().findByEmail(email);
            PersonaDAO.getInstance().deletePersona(p);
        }
    }

    public void ordineInSpedizione(int id){

        Amministratore a = (Amministratore) SessionManager.getInstance().getSession().get("amministratore");

        RichiestaOrdine richiesta = RichiestaOrdineDAO.getInstance().findById(id);

        if(richiesta.getStato().equals("effettuato")){
        RichiestaOrdineDAO.getInstance().setStato("In spedizione", richiesta);
        RichiestaOrdineDAO.getInstance().setAmministratore(a, richiesta);
        }


    }

    public void ordineEvaso(int id){

        Amministratore a = (Amministratore) SessionManager.getInstance().getSession().get("amministratore");

        RichiestaOrdine richiesta = RichiestaOrdineDAO.getInstance().findById(id);

        if(richiesta.getStato().equals("In spedizione")){
            RichiestaOrdineDAO.getInstance().setStato("Evaso", richiesta);
            RichiestaOrdineDAO.getInstance().setAmministratore(a, richiesta);
        }


    }

    public void ordineRifiutato(int id){

        Amministratore a = (Amministratore) SessionManager.getInstance().getSession().get("amministratore");

        RichiestaOrdine richiesta = RichiestaOrdineDAO.getInstance().findById(id);

        if(richiesta.getStato().equals("effettuato")){
            RichiestaOrdineDAO.getInstance().setStato("Rifiutato", richiesta);
            RichiestaOrdineDAO.getInstance().setAmministratore(a, richiesta);
        }


    }


}



