package it.progettojorick.model;

import java.util.ArrayList;

public class Utente extends Persona {

    private String emailUtente;
    private ArrayList<Pagamento> listaPagamentiSalvati=null;

    public String getEmailUtente() {
        return emailUtente;
    }

    public void setEmailUtente(String emailUtente) {
        this.emailUtente = emailUtente;
    }

    public ArrayList<Pagamento> getListaPagamentiSalvati() {
        return listaPagamentiSalvati;
    }

    public void setListaPagamentiSalvati(ArrayList<Pagamento> listaPagamentiSalvati) {
        this.listaPagamentiSalvati = listaPagamentiSalvati;
    }
}
