package it.progettojorick.model;

import java.util.ArrayList;

public class Carrello {

    private int idcarrello;
    //private String emailutente;
    private Utente utente;
    private ArrayList<Prodotto> prodottiContenuti;


    public ArrayList<Prodotto> getProdottiContenuti() {
        return prodottiContenuti;
    }

    public void addProdottoContenuto(Prodotto p){
       // this.prodottiContenuti = new ArrayList<Prodotto>();
       this.prodottiContenuti.add(p);

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
