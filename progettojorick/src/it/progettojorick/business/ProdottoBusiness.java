package it.progettojorick.business;

import it.progettojorick.dao.mysql.CarrelloDAO;
import it.progettojorick.dao.mysql.ProdottoDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.*;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.swing.*;
import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.Iterator;

public class ProdottoBusiness {

    private static ProdottoBusiness instance;

    public static ProdottoBusiness getInstance(){
        if (instance == null)
            instance = new ProdottoBusiness();
        return instance;
    }
    public Prodotto creaProdotto(String nome, String descrizione, float prezzo, int disponibilita, Categoria c, Produttore pr, Distributore d, String imgUrl, int sconto){

    Prodotto p = new Prodotto();
        p.setNome(nome);
        p.setDescrizione(descrizione);
        p.setPrezzo(prezzo);
        p.setDisponibilita(disponibilita);


        p.setCategoria(c);
        p.setProduttore(pr);
        p.setDistributore(d);

        p.setImgUrl(imgUrl);
        p.setSconto(sconto);

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

    public void rimuoviProdotto(String nome){

            ProdottoDAO.getInstance().deleteProdotto(nome);


    }

    public int getQuantita(Carrello c, Prodotto p){


        return Integer.parseInt(ProdottoDAO.getInstance().getQuantita(c.getIdcarrello(),p.getNome()));

    }

    public void setQuantita(int quantita,Carrello c, Prodotto p){

        ProdottoDAO.getInstance().setQuantita(String.valueOf(quantita),c.getIdcarrello(),p.getNome());

    }

    public Prodotto trovaProdotto(String nome){

        return ProdottoDAO.getInstance().findByName(nome);

    }

    public void inserisciProdottiContenuti(Prodotto prodComposto, ArrayList<Prodotto> prodContenuti){

        Iterator i = prodContenuti.iterator();

        while(i.hasNext()){

            Prodotto p = (Prodotto) i.next();
            ProdottoDAO.getInstance().insertProdottiContenuti(prodComposto.getNome(), p.getNome());

        }


    }

    public void setDisponibilita(Prodotto p, int disponibilita){

        ProdottoDAO.getInstance().setDisponibilita(p.getNome(),disponibilita);

    }

    public void setPrezzo(Prodotto p, float prezzo){

        ProdottoDAO.getInstance().setPrezzo(p.getNome(),prezzo);

    }
    public void setSconto(Prodotto p, int sconto){

        if(sconto<0 || sconto>100)
            JOptionPane.showMessageDialog(null, "Valore inserito non valido.");
        else
            ProdottoDAO.getInstance().setSconto(p.getNome(),sconto);

    }

    public ArrayList<Prodotto> trovaPerFasciaDiPrezzo(float inizio, float fine){

        ArrayList<Prodotto> prodottiFiltrati = new ArrayList<Prodotto>();

        try {
            ArrayList<Prodotto> tuttiProdotti = ProdottoBusiness.getInstance().prodottiPresenti();



            Iterator i = tuttiProdotti.iterator();

            while (i.hasNext()) {

                Prodotto p = (Prodotto) i.next();
                float prezzo = p.getPrezzo();
                if (prezzo >= inizio && prezzo <= fine) {
                    prodottiFiltrati.add(p);
                }


            }
            return prodottiFiltrati;
        }catch(NullPointerException ex){

        }

        return null;
    }

    public ArrayList<Prodotto> trovaPerFasciaDiPrezzo(float inizio){

        try {
            ArrayList<Prodotto> tuttiProdotti = ProdottoBusiness.getInstance().prodottiPresenti();
            ArrayList<Prodotto> prodottiFiltrati = new ArrayList<Prodotto>();

            Iterator i = tuttiProdotti.iterator();

            while (i.hasNext()) {

                Prodotto p = (Prodotto) i.next();
                float prezzo = p.getPrezzo();
                if (prezzo >= inizio) {
                    prodottiFiltrati.add(p);
                }


            }
            return prodottiFiltrati;
        } catch (NullPointerException ex){

        }

        return null;
    }

}
