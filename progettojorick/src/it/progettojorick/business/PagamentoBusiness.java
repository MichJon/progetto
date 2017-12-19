package it.progettojorick.business;

import it.progettojorick.dao.mysql.PagamentoDAO;
import it.progettojorick.dao.mysql.UtenteDAO;
import it.progettojorick.model.Pagamento;
import it.progettojorick.model.Utente;
import it.progettojorick.view.PagamentoDatiFrame;

import javax.swing.*;
import java.util.ArrayList;

public class PagamentoBusiness {
    private static PagamentoBusiness instance;

    public static PagamentoBusiness getInstance(){
        if (instance == null)
            instance = new PagamentoBusiness();
        return instance;
    }

    public ArrayList<Pagamento> pagamentiSalvati(Utente u){

        return UtenteDAO.getInstance().pagamentiSalvati(u.getEmailUtente());

    }

    public void inserisciPagamento(long numCarta, String circuito, int cvv, String data){

        try {
            PagamentoDAO.getInstance().insertPagamento(numCarta, circuito, cvv, data);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "dati inseriti non validi.");
        }
    }

public Pagamento trovaPagamento(long numCarta){

        return PagamentoDAO.getInstance().findByNumcarta(numCarta);

}

    public void inserisciPagamentoUtente(Utente u, Pagamento p){


        PagamentoDAO.getInstance().insertPagamentoUtente(u.getEmailUtente(),p.getNumCarta());

    }



}
