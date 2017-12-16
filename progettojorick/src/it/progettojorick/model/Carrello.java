package it.progettojorick.model;

import java.util.ArrayList;

public class Carrello {

    private int idcarrello;
    //private String emailutente;
    private Utente utente;
    ArrayList<Prodotto> prodottiContenuti;

    public ArrayList<Prodotto> getProdottiContenuti() {
        return prodottiContenuti;
    }

    public void setProdottiContenuti(ArrayList<Prodotto> prodottiContenuti) {
        this.prodottiContenuti = prodottiContenuti;
    }

    public int getIdcarrello() {
        return idcarrello;
    }

    public void setIdcarrello(int idcarrello) {
        this.idcarrello = idcarrello;
    }

   /* public String getEmailutente() {
        return emailutente;
    }

    public void setEmailutente(String emailutente) {
        this.emailutente = emailutente;
    }*/

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
