package it.progettojorick.model;

import java.util.ArrayList;

public class Paniere {

    private int idpaniere;
    private String nome;
  /*  private String emailutente;
    private int idcarrello;
*/
    private Utente utente;
    private Carrello carrello;
    private ArrayList<Prodotto> prodotti;

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

   /* public String getEmailutente() {
        return emailutente;
    }

    public void setEmailutente(String emailutente) {
        this.emailutente = emailutente;
    }

    public int getIdcarrello() {
        return idcarrello;
    }

    public void setIdcarrello(int idcarrello) {
        this.idcarrello = idcarrello;
    }*/

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public int getIdpaniere() {
        return idpaniere;
    }

    public void setIdpaniere(int idpaniere) {
        this.idpaniere = idpaniere;
    }
}
