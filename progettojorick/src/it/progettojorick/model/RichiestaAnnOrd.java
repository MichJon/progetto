package it.progettojorick.model;

public class RichiestaAnnOrd {

    private int id;
    private Amministratore amministratore;
    private Utente utente;
    private RichiestaOrdine richiestaDaAnnullare;
    private String stato;

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Amministratore getAmministratore() {
        return amministratore;
    }

    public void setAmministratore(Amministratore amministratore) {
        this.amministratore = amministratore;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public RichiestaOrdine getRichiestaDaAnnullare() {
        return richiestaDaAnnullare;
    }

    public void setRichiestaDaAnnullare(RichiestaOrdine richiestaDaAnnullare) {
        this.richiestaDaAnnullare = richiestaDaAnnullare;
    }
}
