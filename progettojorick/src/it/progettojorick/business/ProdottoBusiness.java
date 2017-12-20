package it.progettojorick.business;

import it.progettojorick.dao.mysql.CarrelloDAO;
import it.progettojorick.dao.mysql.ProdottoDAO;
import it.progettojorick.model.*;

import javax.swing.*;
import java.util.ArrayList;

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

        if (ProdottoDAO.getInstance().findByName(p.getNome())==null) {
            ProdottoDAO.getInstance().insertProdotto(p);
            JOptionPane.showMessageDialog(null,"Prodotto inserito.");
        }
        else JOptionPane.showMessageDialog(null,"Prodotto già presente.");
    }

    public ArrayList<Prodotto> prodottiPresenti(){
        return ProdottoDAO.getInstance().findAll();

    }

    public ArrayList<Prodotto> prodottiPerCategoria(Categoria c){
        return ProdottoDAO.getInstance().findByCategoria(c.getNomecategoria());

    }


}
