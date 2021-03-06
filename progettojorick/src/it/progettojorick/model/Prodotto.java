package it.progettojorick.model;



import java.util.ArrayList;

public class Prodotto {

    private String nome;
    private String descrizione;
    private float prezzo;
    private int disponibilita;
    private Carrello carrello;
    private Categoria categoria;
    private Produttore produttore;
    private Distributore distributore;
    private ArrayList<Prodotto> prodottiContenuti=null;
    private String imgUrl;
    private int Sconto;
    private boolean dalPaniere=false;

    public int getSconto() {
        return Sconto;
    }

    public void setSconto(int sconto) {
        Sconto = sconto;
    }

    public boolean isDalPaniere() {
        return dalPaniere;
    }

    public void setDalPaniere(boolean dalPaniere) {
        this.dalPaniere = dalPaniere;
    }

    public ArrayList<Prodotto> getProdottiContenuti() {
        return prodottiContenuti;
    }

    public void setProdottiContenuti(ArrayList<Prodotto> prodottiContenuti) {
        this.prodottiContenuti = prodottiContenuti;
    }
    /*private int idcarrello;
    private String categoria;
    private int idproduttore;
    private int iddistributore;*/


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public int getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(int disponibilita) {
        this.disponibilita = disponibilita;
    }

  /*  public int getIdcarrello() {
        return idcarrello;
    }

    public void setIdcarrello(int idcarrello) {
        this.idcarrello = idcarrello;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getIdproduttore() {
        return idproduttore;
    }

    public void setIdproduttore(int idproduttore) {
        this.idproduttore = idproduttore;
    }

    public int getIddistributore() {
        return iddistributore;
    }

    public void setIddistributore(int iddistributore) {
        this.iddistributore = iddistributore;
    }*/

    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Produttore getProduttore() {
        return produttore;
    }

    public void setProduttore(Produttore produttore) {
        this.produttore = produttore;
    }

    public Distributore getDistributore() {
        return distributore;
    }

    public void setDistributore(Distributore distributore) {
        this.distributore = distributore;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
