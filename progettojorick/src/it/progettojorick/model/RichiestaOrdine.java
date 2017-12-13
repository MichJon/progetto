package it.progettojorick.model;

public class RichiestaOrdine {

    private int idRichiesta;
    private Amministratore amministratore;
    private String stato;
    private Carrello carrello;
    private Utente utente;
    private Pagamento pagamento;

    public int getIdRichiesta() {
        return idRichiesta;
    }

    public void setIdRichiesta(int idRichiesta) {
        this.idRichiesta = idRichiesta;
    }

    public Amministratore getAmministratore() {
        return amministratore;
    }

    public void setAmministratore(Amministratore amministratore) {
        this.amministratore = amministratore;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}
