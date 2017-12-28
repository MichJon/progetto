package it.progettojorick.business;

import it.progettojorick.dao.mysql.PersonaDAO;
import it.progettojorick.dao.mysql.RichiestaOrdineDAO;
import it.progettojorick.dao.mysql.RichiestaRegistrazioneDAO;
import it.progettojorick.dao.mysql.UtenteDAO;
import it.progettojorick.model.*;
import utilities.Mailer;
import utilities.PDF;
import utilities.StampaNomiProdotti;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

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

            Carrello c = richiesta.getCarrello();
            Utente u = richiesta.getUtente();
            Persona p = PersonaDAO.getInstance().findByEmail(u.getEmailUtente());
            ArrayList<Prodotto> prodottiOrdinati = richiesta.getCarrello().getProdottiContenuti();
            try{
                Mailer.send("jorickshop@gmail.com","progettouniversita",u.getEmailUtente(),"Stato spedizione ordine #"+richiesta.getIdRichiesta(),"Gentile " +p.getNome()+" "+ p.getCognome()+
                        ", il suo ordine che comprende "+ StampaNomiProdotti.getInstance().Stampa(prodottiOrdinati,c)+ "è stato messo in spedizione.");
                RichiestaOrdineDAO.getInstance().setStato("In spedizione", richiesta);
                RichiestaOrdineDAO.getInstance().setAmministratore(a, richiesta);
            }catch (RuntimeException e){
                JOptionPane.showMessageDialog(null,"Controllare la connessione.");
            }

        }


    }

    public void ordineEvaso(int id){

        Amministratore a = (Amministratore) SessionManager.getInstance().getSession().get("amministratore");

        RichiestaOrdine richiesta = RichiestaOrdineDAO.getInstance().findById(id);

        if(richiesta.getStato().equals("In spedizione")){

            Carrello c = richiesta.getCarrello();
            Utente u = richiesta.getUtente();
            Persona p = PersonaDAO.getInstance().findByEmail(u.getEmailUtente());
            ArrayList<Prodotto> prodottiOrdinati = richiesta.getCarrello().getProdottiContenuti();
            try {
                PDF.getInstance().creaPDF(id);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Errore.");
            }
            try{
                Mailer.send("jorickshop@gmail.com","progettouniversita",u.getEmailUtente(),"Stato spedizione ordine #"+richiesta.getIdRichiesta(),"Gentile " +p.getNome()+" "+ p.getCognome()+
                        ", il suo ordine che comprende "+ StampaNomiProdotti.getInstance().Stampa(prodottiOrdinati,c)+ "è stato evaso. In allegato può trovare la ricevuta.","./PDF's/"+u.getEmailUtente()+"#"+richiesta.getIdRichiesta()+".pdf","Ricevuta ordine #"+richiesta.getIdRichiesta()+".pdf");
                RichiestaOrdineDAO.getInstance().setStato("Evaso", richiesta);
                RichiestaOrdineDAO.getInstance().setAmministratore(a, richiesta);
            }catch (RuntimeException e){
                JOptionPane.showMessageDialog(null,"Controllare la connessione.");
            }
        }


    }

    public void ordineRifiutato(int id){

        Amministratore a = (Amministratore) SessionManager.getInstance().getSession().get("amministratore");

        RichiestaOrdine richiesta = RichiestaOrdineDAO.getInstance().findById(id);

        if(richiesta.getStato().equals("effettuato")){

            Carrello c = richiesta.getCarrello();
            Utente u = richiesta.getUtente();
            Persona p = PersonaDAO.getInstance().findByEmail(u.getEmailUtente());
            ArrayList<Prodotto> prodottiOrdinati = richiesta.getCarrello().getProdottiContenuti();
            try{
                Mailer.send("jorickshop@gmail.com","progettouniversita",u.getEmailUtente(),"Stato spedizione ordine #"+richiesta.getIdRichiesta(),"Gentile " +p.getNome()+" "+ p.getCognome()+
                        ", il suo ordine che comprende "+ StampaNomiProdotti.getInstance().Stampa(prodottiOrdinati,c)+ "è stato rifiutato.");
                RichiestaOrdineDAO.getInstance().setStato("Rifiutato", richiesta);
                RichiestaOrdineDAO.getInstance().setAmministratore(a, richiesta);
            }catch (RuntimeException e){
                JOptionPane.showMessageDialog(null,"Controllare la connessione.");
            }
        }


    }


}



