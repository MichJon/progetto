package it.progettojorick.model;

public class PreferenzeConsegna {

    private Utente utente;
    private String indirizzoConsegna;
    private String nominativo;

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getIndirizzoConsegna() {
        return indirizzoConsegna;
    }

    public void setIndirizzoConsegna(String indirizzoConsegna) {
        this.indirizzoConsegna = indirizzoConsegna;
    }

    public String getNominativo() {
        return nominativo;
    }

    public void setNominativo(String nominativo) {
        this.nominativo = nominativo;
    }
}
