package it.progettojorick.business;

import it.progettojorick.dao.mysql.ProdottoDAO;
import it.progettojorick.model.Categoria;
import it.progettojorick.model.Distributore;
import it.progettojorick.model.Prodotto;
import it.progettojorick.model.Produttore;

public class ProdottoBusiness {

    private static ProdottoBusiness instance;

    public static ProdottoBusiness getInstance(){
        if (instance == null)
            instance = new ProdottoBusiness();
        return instance;
    }
    public Prodotto creaProdotto(String nome, String descrizione, float prezzo, int quantita, Categoria c, Produttore pr, Distributore d, String imgUrl){

    Prodotto p = new Prodotto();
        p.setNome(nome);
        p.setDescrizione(descrizione);
        p.setPrezzo(prezzo);
        p.setQuantita(quantita);


        p.setCategoria(c);
        p.setProduttore(pr);
        p.setDistributore(d);

        p.setImgUrl(imgUrl);

        return p;
    }


    public void inserisciProdotto(Prodotto p){

        if (ProdottoDAO.getInstance().findByName(p.getNome())==null)
        ProdottoDAO.getInstance().insertProdotto(p);

    }



}
