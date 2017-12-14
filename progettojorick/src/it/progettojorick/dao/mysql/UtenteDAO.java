package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.IUtenteDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.Pagamento;
import it.progettojorick.model.Persona;
import it.progettojorick.model.Utente;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Iterator;

public class UtenteDAO implements IUtenteDAO {

        //singleton design pattern
        private static UtenteDAO instance;

        public static UtenteDAO getInstance(){
            if (instance==null)
                instance = new UtenteDAO();
            return instance;
        }



        @Override
        public Utente findByEmail(String email){

            ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM utente WHERE persona_email = '"+email+"';");

            if (risultato.size()==0) return null;

            Utente u=new Utente();

            String[] riga= risultato.get(0);
            u.setEmailUtente(riga[0]);


            return u;

        }
        @Override
        public ArrayList<Utente> findAll() {

            ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM utente");

            if (risultato.size()==0) return null;
            ArrayList<Utente> listaUtenti = new ArrayList<Utente>();

            Iterator<String[]> i = risultato.iterator();

            while(i.hasNext()) {
                String[] riga = i.next();
                Utente u = new Utente();
                u.setEmailUtente(riga[0]);


                listaUtenti.add(u);
            }
            return listaUtenti;

        }



    public ArrayList<Pagamento> pagamentiSalvati (String email){

        ArrayList<String []>  risNomiProdotti= DbConnection.getInstance().eseguiQuery("SELECT pagamento_num_carta FROM utente_has_pagamento WHERE utente_persona_email = '" +email+"';" );
        ArrayList<Pagamento> pagamenti = new ArrayList<Pagamento>();
        if (risNomiProdotti.size()==0) return null;
        Iterator<String[]> i = risNomiProdotti.iterator();

        while (i.hasNext()){
            String[] riga = i.next();
            Pagamento pagamento = PagamentoDAO.getInstance().findByNumcarta(Integer.parseInt(riga[0]));
            pagamenti.add(pagamento);
        }
        return pagamenti;
    }

    @Override
    public void insertUtente(String email) {
        DbConnection.getInstance().eseguiAggiornamento("INSERT INTO utente (persona_email)"+
                " VALUES ('"+email+"');");
    }



}


