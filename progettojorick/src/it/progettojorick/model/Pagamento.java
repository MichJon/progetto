package it.progettojorick.model;

public class Pagamento {

    private int numCarta;
    private String circuito;
    private int codSicurezza;
    private String dataScadenza;

    public int getNumCarta() {
        return numCarta;
    }

    public void setNumCarta(int numCarta) {
        this.numCarta = numCarta;
    }

    public String getCircuito() {
        return circuito;
    }

    public void setCircuito(String circuito) {
        this.circuito = circuito;
    }

    public int getCodSicurezza() {
        return codSicurezza;
    }

    public void setCodSicurezza(int codSicurezza) {
        this.codSicurezza = codSicurezza;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }
}
