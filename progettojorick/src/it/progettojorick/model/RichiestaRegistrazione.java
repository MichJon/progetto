package it.progettojorick.model;

public class RichiestaRegistrazione {
    private int idRegistrazione;
    private Amministratore amministratore;
    private Persona persona;
    private String stato="In attesa di conferma...";

    public int getIdRegistrazione() {
        return idRegistrazione;
    }

    public void setIdRegistrazione(int idRegistrazione) {
        this.idRegistrazione = idRegistrazione;
    }

    public Amministratore getAmministratore() {
        return amministratore;
    }

    public void setAmministratore(Amministratore amministratore) {
        this.amministratore = amministratore;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}
