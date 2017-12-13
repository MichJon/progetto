package it.progettojorick.model;

public class Carrello {

    private int idcarrello;
    //private String emailutente;
    private Utente utente;



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
