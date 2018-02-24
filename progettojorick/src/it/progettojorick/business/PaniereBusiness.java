package it.progettojorick.business;

import it.progettojorick.dao.mysql.PaniereDAO;
import it.progettojorick.model.Paniere;
import it.progettojorick.model.Prodotto;
import it.progettojorick.model.Utente;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

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

        Prodotto prod = ProdottoBusiness.getInstance().trovaProdotto(nomeProdotto);
        ArrayList<Prodotto> prodCont = prodottiContenuti(pan);
        boolean presente = false;
        if(prodCont!=null) {
            Iterator i = prodCont.iterator();


            while (i.hasNext()) {

                Prodotto iterProd = (Prodotto) i.next();

                if (iterProd.getNome().equals(prod.getNome())) {
                    presente = true;
                    JOptionPane.showMessageDialog(null, "Il prodotto è già presente nel paniere.");
                }
            }
        }
        if (!presente) {
            PaniereDAO.getInstance().insertProdottoInPaniere(pan.getIdpaniere(), nomeProdotto);
        }
            Paniere p = PaniereDAO.getInstance().findById(pan.getIdpaniere());

            SessionManager.getInstance().getSession().put("paniere", p);



    }

    public void cancellaProdottoDalPaniere(String nomeProdotto, Paniere pan){

        PaniereDAO.getInstance().deleteProdottoFromPaniere(nomeProdotto, pan.getIdpaniere());
        //PaniereDAO.getInstance().
    }


    public void inserisciPaniere(String nome, Utente u){

        PaniereDAO.getInstance().insertPaniere(nome, u.getEmailUtente());

    }

    public void eliminaPaniere(Paniere pan){

        PaniereDAO.getInstance().deletePaniere(pan.getIdpaniere());

    }


}
