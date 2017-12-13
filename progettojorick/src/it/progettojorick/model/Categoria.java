package it.progettojorick.model;

public class Categoria {
    private String nomecategoria;
    private GestoreCatalogo gestoreCatalogo;

    public String getNomecategoria() {
        return nomecategoria;
    }

    public void setNomecategoria(String nomecategoria) {
        this.nomecategoria = nomecategoria;
    }

    public GestoreCatalogo getGestoreCatalogo() {
        return gestoreCatalogo;
    }

    public void setGestoreCatalogo(GestoreCatalogo gestoreCatalogo) {
        this.gestoreCatalogo = gestoreCatalogo;
    }
}
